package de.localchat;

import de.localchat.discovery.DiscoveryBackend;
import de.localchat.discovery.DiscoveryListener;
import de.localchat.discovery.DiscoveryRequest;
import de.localchat.discovery.common.DefaultDiscoveryRequest;
import de.localchat.discovery.udp.UDPDiscoveryBackend;

import java.io.IOException;

import static spark.Spark.*;

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

        get("/posts", (req, res) -> {
            return "Hello Sparkingly World!";
        });
        get("/test", "application/json", (req, res) -> {
            String id = req.queryParams("id");
            return "HI " + id;
        });
    }
}