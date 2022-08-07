/*
 * Copyright (C) 2022 Nojang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package de.localchat.network.pool

import de.localchat.network.environment.Environment
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket
import kotlinx.coroutines.flow.Flow
import org.tinylog.kotlin.Logger

abstract class AbstractSocketPool<E : Environment> : SocketPool {
    protected val environment: E

    protected val clientSockets = mutableListOf<ClientSocket>()
    protected val serverSockets = mutableListOf<ServerSocket>()
    protected val datagramSockets = mutableListOf<DatagramSocket>()

    init {
        val environmentFactory = newEnvironmentFactory()
        environment = environmentFactory.newEnvironment()

        Logger.info("Creating socket pool with environment: {}", environment.getName())
    }

    abstract fun newEnvironmentFactory(): Environment.Factory<E>

    override fun connect(name: String, remoteAddress: String, port: Int): ClientSocket {
        TODO("Not yet implemented")
    }

    override fun bind(name: String, port: Int): ServerSocket {
        TODO("Not yet implemented")
    }

    override fun bindDatagram(name: String, port: Int): DatagramSocket {
        TODO("Not yet implemented")
    }

    override fun close() {
        Logger.debug("Closing socket pool")

        clientSockets.forEach { it.close() }
        serverSockets.forEach { it.close() }
        datagramSockets.forEach { it.close() }

        clientSockets.clear()
        serverSockets.clear()
        datagramSockets.clear()
    }

    override fun onClose(): Flow<Nothing> {
        TODO("Not yet implemented")
    }
}