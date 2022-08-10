package de.localchat.network.hold

import de.localchat.common.lifecycle.Closeable

interface SocketPool : Closeable {
    fun connect(name: String, remoteAddress: String, port: Int): ClientSocket
    fun bind(name: String, port: Int): ServerSocket
    fun bindDatagram(name: String, port: Int): DatagramSocket
}