package de.localchat.network.netty.resources

import io.netty5.channel.Channel
import io.netty5.channel.ChannelFactory
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

interface NettyResources : AutoCloseable {
    val name: String
    val eventLoopGroup: EventLoopGroup

    fun newHandlerFactory(): IoHandlerFactory
    fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel>

    companion object {
        fun nativeResources(): NettyResources {
            return if (Epoll.isAvailable()) {
                EpollNettyResources()
            } else if (KQueue.isAvailable()) {
                KQueueNettyResources()
            } else {
                NIONettyResources()
            }
        }
    }
}