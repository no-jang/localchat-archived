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

package de.localchat.network.netty.environment

import de.localchat.network.Environment
import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

/**
 * Defines the environment dependant components that are required to start a netty server/client.
 */
interface NettyEnvironment : Environment {
    /**
     * [Environment.Factory] for [NettyEnvironment]s.
     */
    interface Factory : Environment.Factory<NettyEnvironment>

    /**
     * Creates new [IoHandlerFactory] usally used for creating an [io.netty5.channel.EventLoopGroup].
     */
    fun newHandlerFactory(): IoHandlerFactory

    /**
     * Creates new [ChannelFactory] that creates new datagram channels for udp connections.
     */
    fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel>

    /**
     * Creates new [ChannelFactory] that creates new socket channels for tcp connections to a server.
     */
    fun newSocketChannelFactory(): ChannelFactory<SocketChannel>

    /**
     * Creates new [ChannelFactory] that creates new socket channels for server tcp bindings.
     */
    fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel>
}
