package de.localchat.network.hold

interface Connection {
    fun getPort(): Int

    fun getRemoteAddress(): String
}