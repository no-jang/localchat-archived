package de.localchat.network.socket

interface ClientSocket : Socket {
    val remoteAddress: String
}