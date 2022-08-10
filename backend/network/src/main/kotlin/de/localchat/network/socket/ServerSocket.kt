package de.localchat.network.socket

interface ServerSocket : Socket {
    interface Factory : Socket.Factory {
        fun newServerSocket(name: String, port: Int): ServerSocket
    }
}