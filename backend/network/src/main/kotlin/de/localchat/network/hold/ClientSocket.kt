package de.localchat.network.hold

interface ClientSocket : Socket {
    val remoteAddress: String
}