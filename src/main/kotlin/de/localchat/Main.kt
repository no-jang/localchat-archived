package de.localchat

import de.localchat.discovery.common.DefaultClientDiscovery
import de.localchat.discovery.udp.UDPDiscoveryBackend
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    val discoveryBackend = UDPDiscoveryBackend()

    runBlocking {
        launch {
            discoveryBackend.open().collect {
                println("Discovered: $it")
            }
        }

        launch {
            while (true) {
                discoveryBackend.send(DefaultClientDiscovery("marek", "Hello World", 1234))
                delay(1500L)
            }
        }
    }
}