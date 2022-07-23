package de.localchat.network.netty.udp

import de.localchat.network.udp.UDPConnection
import io.netty5.channel.socket.DatagramChannel
import org.tinylog.kotlin.Logger

class UDPNettyConnection(
    val name: String,
    val port: Int,
    private val network: UDPNettyNetwork,
    private val channel: DatagramChannel
) : UDPConnection {
    override fun send(o: Any) {
        channel.writeAndFlush(o)
    }

    override fun close() {
        Logger.debug("Close network: {}, connection {}, port: {}", network.name, name, port)
        channel.close()
        network.connectionClosed(this)
    }
}