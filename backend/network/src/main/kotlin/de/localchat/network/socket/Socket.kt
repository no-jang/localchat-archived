package de.localchat.network.socket

import de.localchat.common.lifecycle.Closeable
import de.localchat.network.connection.Connection

interface Socket : Closeable {
    interface Factory

    val name: String
    val port: Int
    val connections: List<Connection>
}