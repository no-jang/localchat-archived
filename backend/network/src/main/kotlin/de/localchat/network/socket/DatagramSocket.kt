package de.localchat.network.socket

interface DatagramSocket : Socket {
    interface Factory : Socket.Factory {
        fun newDatagramSocket(name: String, port: Int): DatagramSocket
    }
}