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

package de.localchat.network.netty.old

/*
import de.localchat.network.environment.Environment
import de.localchat.network.netty.environment.NettyEnvironment
import de.localchat.network.netty.environment.NettyEnvironmentFactory
import de.localchat.network.pool.AbstractSocketPool
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.MultithreadEventLoopGroup

class NettySocketPool : AbstractSocketPool<NettyEnvironment>() {
    private val eventLoopGroup: EventLoopGroup

    init {
        eventLoopGroup = MultithreadEventLoopGroup(environment.newHandlerFactory())
    }

    override fun newEnvironmentFactory(): Environment.Factory<NettyEnvironment> = NettyEnvironmentFactory()

    override fun connect(name: String, remoteAddress: String, port: Int): ClientSocket {
        super.connect(name, remoteAddress, port)
    }

    override fun bind(name: String, port: Int): ServerSocket {
        super.bind(name, port)
    }

    override fun bindDatagram(name: String, port: Int): DatagramSocket {
        super.bindDatagram(name, port)
    }

    override fun close() {
        super.close()
        eventLoopGroup.shutdownGracefully()
    }
}*/
