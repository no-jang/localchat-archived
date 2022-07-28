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
import io.netty5.channel.epoll.EpollDatagramChannel
import io.netty5.channel.epoll.EpollHandler
import io.netty5.channel.epoll.EpollServerSocketChannel
import io.netty5.channel.epoll.EpollSocketChannel
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

/**
 * Implementation of the epoll netty environment. It bases on the linux epoll network kernel api and so is only usable
 * on linux based systems.
 */
class EpollNettyEnvironment : NettyEnvironment {
    override fun getName(): String = "epoll"

    override fun newHandlerFactory(): IoHandlerFactory =
        EpollHandler.newFactory()

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> EpollDatagramChannel(eventLoop) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> EpollSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> EpollServerSocketChannel(eventLoop, eventLoopGroup) }
}
