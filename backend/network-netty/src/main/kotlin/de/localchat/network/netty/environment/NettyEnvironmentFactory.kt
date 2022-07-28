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

import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue

/**
 * Implementation of [NettyEnvironment.Factory] for default purposes. There is no need to create another implementation.
 */
class NettyEnvironmentFactory : NettyEnvironment.Factory {
    override fun newEnvironment(): NettyEnvironment {
        return if (Epoll.isAvailable()) {
            EpollNettyEnvironment()
        } else if (KQueue.isAvailable()) {
            KQueueNettyEnvironment()
        } else {
            NIONettyEnvironment()
        }
    }
}