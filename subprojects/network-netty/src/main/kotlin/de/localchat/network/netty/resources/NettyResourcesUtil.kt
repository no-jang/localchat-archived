package de.localchat.network.netty.resources

import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue

object NettyResourcesUtil {
    @JvmStatic
    fun supportedType(): NettyResources.NettyType {
        return if (Epoll.isAvailable()) {
            NettyResources.NettyType.EPOLL
        } else if (KQueue.isAvailable()) {
            NettyResources.NettyType.KQUEUE
        } else {
            NettyResources.NettyType.NIO
        }
    }

    fun newResources(type: NettyResources.NettyType): NettyResources {
        return when (type) {
            NettyResources.NettyType.EPOLL -> EpollNettyResources()
            NettyResources.NettyType.KQUEUE -> KQueueNettyResources()
            NettyResources.NettyType.NIO -> NIONettyResources()
        }
    }
}