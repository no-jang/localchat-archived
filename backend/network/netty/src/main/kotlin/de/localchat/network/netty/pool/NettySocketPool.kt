package de.localchat.network.netty.pool

import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket

class NettySocketPool : EnvironmentalSocketPool() {
    override fun connect(name: String, remoteAddress: String, port: Int): ClientSocket {
        TODO("Not yet implemented")
    }

    override fun bind(name: String, port: Int): ServerSocket {
        TODO("Not yet implemented")
    }

    override fun bindDatagram(name: String, port: Int): DatagramSocket {
        TODO("Not yet implemented")
    }
}