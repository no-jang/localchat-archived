package de.localchat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import de.localchat.web.WebService;
import de.localchat.web.javlin.JavlinWebService;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;

import org.slf4j.*;
import org.json.JSONObject;
import static j2html.TagCreator.*;

public class Main {
    public static void main(String[] args) {
        WebService webService = new JavlinWebService();
    }
}