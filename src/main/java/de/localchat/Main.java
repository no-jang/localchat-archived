package de.localchat;

import de.localchat.discovery.DiscoveryBackend;
import de.localchat.discovery.DiscoveryListener;
import de.localchat.discovery.DiscoveryRequest;
import de.localchat.discovery.common.DefaultDiscoveryRequest;
import de.localchat.discovery.udp.UDPDiscoveryBackend;

import java.io.IOException;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) throws IOException {
        DiscoveryListener listener = new DiscoveryListener() {
            @Override
            public void receivedRequest(DiscoveryRequest request) {
                System.out.println("Discovery received: " + request.getHost() + " web: " + request.getWebPort());
            }
        };

        DiscoveryBackend backend = new UDPDiscoveryBackend(listener);
        backend.broadcastRequest(new DefaultDiscoveryRequest("localhost", 78565));

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(7070);

        app.get("/send", ctx -> {
            String message = ctx.queryParam("message");
            ctx.result("Hello: " + message);
            System.out.println(message);
        });

        app.ws("/ws", ws -> {
            ws.onConnect(ctx -> {
                broadcastMessage("WS connected");
            });
            ws.onClose(ctx -> {
                broadcastMessage("WS disconnected");
            });
            ws.onMessage(ctx -> {
                broadcastMessage(ctx.message());
            });
        });

    }

    private static void broadcastMessage(String message) {
        System.out.println(message);
    }

}