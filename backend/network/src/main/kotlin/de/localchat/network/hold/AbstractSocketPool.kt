package de.localchat.network.hold

import de.localchat.common.lifecycle.AbstractCloseable
import org.slf4j.Logger

abstract class AbstractSocketPool<E : Environment>(
    protected val environment: E,
    protected val logger: Logger
) : AbstractCloseable(), SocketPool {
    protected val clientSockets = mutableListOf<ClientSocket>()
    protected val serverSockets = mutableListOf<ServerSocket>()
    protected val datagramSockets = mutableListOf<DatagramSocket>()

    init {
        logger.debug("create socket pool | environment: {}", environment)
    }

    override fun doClose() {
        logger.debug(
            "Closing socket pool | clients: {}, servers: {}, datagrams: {}",
            clientSockets.size, serverSockets.size, datagramSockets.size
        )

        clientSockets.forEach { it.close() }
        serverSockets.forEach { it.close() }
        datagramSockets.forEach { it.close() }

        clientSockets.clear()
        serverSockets.clear()
        datagramSockets.clear()
    }
}