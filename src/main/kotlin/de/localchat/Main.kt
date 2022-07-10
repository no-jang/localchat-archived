package de.localchat

import de.localchat.discovery.common.CommonDiscovery
import de.localchat.discovery.udp.UDPDiscoveryBackend
import io.netty5.buffer.api.DefaultBufferAllocators
import io.netty5.channel.socket.DatagramPacket
import java.net.InetSocketAddress

fun main(args: Array<String>) {
    val discoveryBackend = UDPDiscoveryBackend()

    while (true) {
        //val buffer = DefaultBufferAllocators.preferredAllocator().copyOf("Hello World", Charsets.UTF_8)

        //discoveryBackend.send(DatagramPacket(buffer, InetSocketAddress(UDPDiscoveryBackend.MULTICAST_ADDRESS, UDPDiscoveryBackend.MULTICAST_PORT)))
        discoveryBackend.send(CommonDiscovery("Hello World", 1234))
        Thread.sleep(1500L)
    }
}