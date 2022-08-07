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
import io.netty5.channel.nio.NioHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel
import io.netty5.channel.socket.nio.NioDatagramChannel
import io.netty5.channel.socket.nio.NioServerSocketChannel
import io.netty5.channel.socket.nio.NioSocketChannel

class NIONettyEnvironment : NettyEnvironment {
    override fun getName(): String = "nio"

    override fun newHandlerFactory(): IoHandlerFactory =
        NioHandler.newFactory()

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> NioDatagramChannel(eventLoop) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> NioSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> NioServerSocketChannel(eventLoop, eventLoopGroup) }

}