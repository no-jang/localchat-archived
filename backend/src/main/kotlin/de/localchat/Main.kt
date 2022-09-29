package de.localchat

import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 4567) {
        routing {
            singlePageApplication {
                useResources = true
                filesPath = "static"
                defaultPage = "index.html"
            }
        }
    }.start(wait = true)
}
