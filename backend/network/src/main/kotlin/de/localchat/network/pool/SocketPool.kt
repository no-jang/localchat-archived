package de.localchat.network.pool

import de.localchat.common.lifecycle.Closeable
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket

interface SocketPool : Closeable {
    fun connect(name: String, remoteAddress: String, port: Int): ClientSocket
    fun bind(name: String, port: Int): ServerSocket
    fun bindDatagram(name: String, port: Int): DatagramSocket
}