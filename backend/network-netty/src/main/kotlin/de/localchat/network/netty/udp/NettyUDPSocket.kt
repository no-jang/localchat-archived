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

package de.localchat.network.netty.udp

/*
class NettyUDPSocket(
    val name: String,
    val port: Int,
    private val pool: NettyUDPPool,
    private val channel: DatagramChannel
) {
    fun send(o: Any) {
        channel.writeAndFlush(o)
    }

    fun close() {
        doClose()
        pool.socketClosed(this)
    }

    internal fun doClose() {
        Logger.debug("Close udp socket {}, pool: {}, port: {}", name, pool.name, port)
        channel.close()
    }
}*/
