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
import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue

/**
 * Implementation of [NettyEnvironment.Factory] for default purposes. There is no need to create another implementation.
 */
class NettyEnvironmentFactory : NettyEnvironment.Factory {
    override fun checkSupportedTypes(): List<Environment.Type> {
        val types = mutableListOf<Environment.Type>()

        if (Epoll.isAvailable()) {
            types.add(NettyEnvironment.Type.EPOLL)
        } else if (KQueue.isAvailable()) {
            types.add(NettyEnvironment.Type.KQUEUE)
        }

        types.add(NettyEnvironment.Type.NIO)

        return types
    }

    override fun newEnvironment(type: Environment.Type): NettyEnvironment {
        return when (type) {
            NettyEnvironment.Type.EPOLL -> EpollNettyEnvironment()
            NettyEnvironment.Type.KQUEUE -> KQueueNettyEnvironment()
            NettyEnvironment.Type.NIO -> NIONettyEnvironment()
            else -> {
                throw IllegalArgumentException("Unknown netty environment type: $type")
            }
        }
    }
}