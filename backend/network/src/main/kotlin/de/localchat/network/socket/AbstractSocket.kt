package de.localchat.network.socket

import de.localchat.common.lifecycle.AbstractCloseable
import de.localchat.network.connection.Connection
import de.localchat.network.environment.Environment
import org.slf4j.Logger

abstract class AbstractSocket<E : Environment>(
    final override val name: String,
    final override val port: Int,
    protected val environment: E,
    protected val logger: Logger
) : AbstractCloseable(), Socket {
    override val connections: MutableList<Connection> = mutableListOf()

    init {
        logger.debug("Creating socket | name: {} port: {}", name, port)
    }

    override fun doClose() {
        logger.debug("Closing socket | name: {} port: {}", name, port)
    }
}