package de.localchat.network.pool

import de.localchat.common.lifecycle.AbstractCloseable
import de.localchat.network.environment.Environment
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket
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

    override fun close() {
        logger.debug(
            "close socket pool | clients: {}, servers: {}, datagrams: {}",
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