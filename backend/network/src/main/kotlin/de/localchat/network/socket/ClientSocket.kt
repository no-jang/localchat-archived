package de.localchat.network.socket

interface ClientSocket : Socket {
    interface Factory : Socket.Factory {
        fun newClientSocket(name: String, remoteAddress: String, port: Int): ClientSocket
    }

    val remoteAddress: String
}