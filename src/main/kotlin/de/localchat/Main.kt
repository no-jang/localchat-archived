package de.localchat

import de.localchat.discovery.common.CommonDiscovery
import de.localchat.discovery.udp.UDPDiscoveryBackend

fun main(args: Array<String>) {
    val discoveryBackend = UDPDiscoveryBackend()

    while (true) {
        discoveryBackend.send(CommonDiscovery("localhost", 4567))
    }
}