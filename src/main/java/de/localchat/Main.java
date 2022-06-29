package de.localchat;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/posts", (req, res) -> {
            return "Hello Sparkingly World!";
        });
    }
}