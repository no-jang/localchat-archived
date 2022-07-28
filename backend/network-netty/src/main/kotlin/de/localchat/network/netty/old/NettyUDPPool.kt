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

import de.localchat.network.netty.environment.NettyEnvironmentFactory
import de.localchat.network.netty.util.waitForFuture
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.ChannelInitializer
import io.netty5.channel.ChannelOption
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.MultithreadEventLoopGroup
import io.netty5.channel.socket.DatagramChannel
import org.tinylog.kotlin.Logger

class NettyUDPPool(val name: String) {
    private val eventLoopGroup: EventLoopGroup
    private val bootstrap: Bootstrap

    private val sockets = mutableListOf<NettyUDPSocket>()

    init {
        val environmentFactory = NettyEnvironmentFactory()
        val environmentType = environmentFactory.getSuggestedType()

        Logger.info("Creating udp pool {} environment: {}", name, environmentType)

        val environment = environmentFactory.newEnvironment(environmentType)

        eventLoopGroup = MultithreadEventLoopGroup(environment.newHandlerFactory())
        bootstrap = Bootstrap()
            .option(ChannelOption.SO_REUSEADDR, true)
            .group(eventLoopGroup)
            .channelFactory(environment.newDatagramChannelFactory())
            .handler(object : ChannelInitializer<DatagramChannel>() {
                override fun initChannel(ch: DatagramChannel?) {
                    //TODO("Not yet implemented")
                }
            })
    }

    fun bind(name: String, port: Int): NettyUDPSocket {
        Logger.debug("Bind udp socket {}, pool: {}, port: {}", name, this.name, port)

        val channel = bootstrap.bind(port).waitForFuture() as DatagramChannel

        val socket = NettyUDPSocket(name, port, this, channel)
        sockets.add(socket)

        return socket
    }

    fun close() {
        Logger.info("Close udp pool {}", name)

        // TODO Add closing handler
        for (socket in sockets) {
            socket.doClose()
        }
        sockets.clear()

        eventLoopGroup.shutdownGracefully()
    }

    internal fun socketClosed(socket: NettyUDPSocket) {
        sockets.remove(socket)
    }
}