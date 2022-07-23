package de.localchat.network.udp

interface UDPConnection {
    // TODO Move to sender to send exact objects
    fun send(o: Any)

    fun close()
}