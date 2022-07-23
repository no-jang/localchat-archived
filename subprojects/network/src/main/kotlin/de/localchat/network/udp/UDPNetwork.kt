package de.localchat.network.udp

interface UDPNetwork {
    var port: Int?

    fun bind(name: String): UDPConnection
    fun close()
}