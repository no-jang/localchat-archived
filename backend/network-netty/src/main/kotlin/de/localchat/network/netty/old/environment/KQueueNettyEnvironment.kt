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

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.kqueue.KQueueDatagramChannel
import io.netty5.channel.kqueue.KQueueHandler
import io.netty5.channel.kqueue.KQueueServerSocketChannel
import io.netty5.channel.kqueue.KQueueSocketChannel
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

/**
 * Implementation of the netty kqueue environment. It bases on the bsd kernel kqueue network interface and so is
 * only usable on BSD based systems.
 */
class KQueueNettyEnvironment : NettyEnvironment {
    override fun getName(): String = "kqueue"

    override fun newHandlerFactory(): IoHandlerFactory =
        KQueueHandler.newFactory()

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> KQueueDatagramChannel(eventLoop) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> KQueueSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> KQueueServerSocketChannel(eventLoop, eventLoopGroup) }
}
