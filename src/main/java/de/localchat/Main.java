package de.localchat;

import org.tinylog.Logger;

import java.io.IOException;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) throws IOException {
        staticFiles.location("/public");

        get("/posts", (req, res) -> {
            res.redirect("composer.html");return null;
        });
        get("/send", (req, res) -> {
            String message = req.queryParams("message");
            System.out.println(message);
            return "HI " + message;
        });

    }
}