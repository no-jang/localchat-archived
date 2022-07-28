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

package de.localchat.network.netty.socket

import de.localchat.network.netty.environment.NettyEnvironment
import de.localchat.network.netty.util.waitForFuture
import de.localchat.network.socket.AbstractDatagramSocket
import de.localchat.network.socket.DatagramSocket
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.ChannelInitializer
import io.netty5.channel.ChannelOption
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.socket.DatagramChannel
import org.tinylog.kotlin.Logger

class NettyDatagramSocket(name: String, port: Int, private val channel: DatagramChannel) :
    AbstractDatagramSocket(name, port) {
    class Factory(environment: NettyEnvironment, eventLoopGroup: EventLoopGroup) : DatagramSocket.Factory {
        private val bootstrap: Bootstrap = Bootstrap()
            .option(ChannelOption.SO_REUSEADDR, true)
            .group(eventLoopGroup)
            .channelFactory(environment.newDatagramChannelFactory())
            .handler(object : ChannelInitializer<DatagramChannel>() {
                override fun initChannel(ch: DatagramChannel?) {
                    TODO("Not yet implemented")
                }
            })

        override fun newDatagramSocket(name: String, port: Int): DatagramSocket {
            val channel = bootstrap.bind(port).waitForFuture() as DatagramChannel
            return NettyDatagramSocket(name, port, channel)
        }
    }

    override fun close() {
        Logger.debug("Close datagram socket {} port: {}", getName(), getPort())

        super.close()
    }
}