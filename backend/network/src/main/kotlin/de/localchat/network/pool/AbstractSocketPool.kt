package de.localchat.network.pool

import de.localchat.common.lifecycle.AbstractCloseable
import de.localchat.network.environment.Environment
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket

abstract class AbstractSocketPool(
    environmentFactory:
) : AbstractCloseable(), SocketPool {
    protected val clientSockets = mutableListOf<ClientSocket>()
    protected val serverSockets = mutableListOf<ServerSocket>()
    protected val datagramSockets = mutableListOf<DatagramSocket>()

    protected val environment: Environment

    protected abstract fun newEnvironmentFactory
}