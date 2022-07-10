package de.localchat.network.netty.resources

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily

interface NettyResources {
    enum class Type {
        NIO,
        EPOLL,
        KQUEUE
    }

    val type: Type

    fun newHandlerFactory(): IoHandlerFactory
    fun newDatagramChannelFactory(protocolFamily: InternetProtocolFamily): ChannelFactory<DatagramChannel>

    companion object {
        fun nativeType(): Type {
            return if (Epoll.isAvailable()) {
                Type.EPOLL
            } else if (KQueue.isAvailable()) {
                Type.KQUEUE
            } else {
                Type.NIO
            }
        }

        fun nativeResources(): NettyResources {
            return when (nativeType()) {
                Type.EPOLL -> EpollNettyResources()
                Type.KQUEUE -> KQueueNettyResources()
                else -> NIONettyResources()
            }
        }
    }
}