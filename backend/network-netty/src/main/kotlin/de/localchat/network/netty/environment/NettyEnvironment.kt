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

import de.localchat.network.environment.Environment
import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

interface NettyEnvironment : Environment {
    interface Factory : Environment.Factory<NettyEnvironment>

    fun newHandlerFactory(): IoHandlerFactory

    fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel>

    fun newSocketChannelFactory(): ChannelFactory<SocketChannel>

    fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel>
}