package de.localchat

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 4567) {
        routing()
    }.start(true)
}

fun Application.routing() {
    routing {
        static("/") {
            staticBasePackage = "public"
            resources(".")
        }

        get("/posts") {
            call.respondRedirect("composer.html")
        }

        get("/send") {
            val message = call.request.queryParameters["message"]
            println(message)
            call.respond("Hi $message")
        }
    }
}