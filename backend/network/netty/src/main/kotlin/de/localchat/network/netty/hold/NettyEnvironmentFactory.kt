package de.localchat.network.netty.hold

import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue

object NettyEnvironmentFactory {
    fun newEnvironment(): NettyEnvironment {
        return if (Epoll.isAvailable()) {
            EpollNettyEnvironment()
        } else if (KQueue.isAvailable()) {
            KQueueNettyEnvironment()
        } else {
            NIONettyEnvironment()
        }
    }
}