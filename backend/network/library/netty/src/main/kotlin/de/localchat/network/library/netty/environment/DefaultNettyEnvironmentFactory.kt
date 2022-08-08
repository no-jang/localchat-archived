package de.localchat.network.library.netty.environment

import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue

class DefaultNettyEnvironmentFactory : NettyEnvironment.Factory {
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