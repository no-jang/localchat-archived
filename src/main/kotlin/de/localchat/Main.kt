package de.localchat

import de.localchat.network.netty.udp.UDPNettyNetwork
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val network = UDPNettyNetwork("test")
        network.port = 1234

        val connection = network.bind("test")

        launch {
            delay(5000)
            network.close()
        }
    }
}