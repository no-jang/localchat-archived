package de.localchat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;

import org.slf4j.*;
import org.json.JSONObject;
import static j2html.TagCreator.*;

public class Main {

    private static Map<WsContext, String> userUsernameMap = new ConcurrentHashMap<>();
    private static int nextUserNumber = 1;

    public static void main(String[] args) throws IOException {
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
                String username = "User" + nextUserNumber++;
                userUsernameMap.put(ctx, username);
                broadcastMessage("Server", (username + " joined the chat"));
            });
            ws.onClose(ctx -> {
                String username = userUsernameMap.get(ctx);
                userUsernameMap.remove(ctx);
                broadcastMessage("Server ", (username + " left the chat"));
            });
            ws.onMessage(ctx -> {
                broadcastMessage(userUsernameMap.get(ctx), ctx.message());
            });
        });

    }

    private static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(ctx -> ctx.session.isOpen()).forEach(session -> {
            session.send(
                    new JSONObject()
                            .put("userMessage", createHtmlMessageFromSender(sender, message))
                            .put("userlist", userUsernameMap.values()).toString()
            );
        });
        System.out.println(sender + " " + message);

    }

    private static String createHtmlMessageFromSender(String sender, String message) {
        return div(attrs(".message"),
                div(attrs(".sender"), sender),
                div(attrs(".text"), message),
                div(attrs(".timestamp"), new SimpleDateFormat("HH:mm:ss").format(new Date()))
        ).render();
    }

}