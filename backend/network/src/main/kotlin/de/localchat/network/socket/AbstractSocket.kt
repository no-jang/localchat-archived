package de.localchat.network.socket

import de.localchat.common.lifecycle.AbstractCloseable
import de.localchat.network.connection.Connection

abstract class AbstractSocket(
    override val name: String,
    override val port: Int
) : AbstractCloseable(), Socket {
    override val connections: MutableList<Connection> = mutableListOf()
}