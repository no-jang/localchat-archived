package de.localchat.network.connection

interface Connection {
    fun getPort(): Int

    fun getRemoteAddress(): String
}