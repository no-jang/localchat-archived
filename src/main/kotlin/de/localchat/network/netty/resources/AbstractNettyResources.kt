package de.localchat.network.netty.resources

import io.netty5.channel.ChannelFactory
import io.netty5.channel.EventLoop
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.MultithreadEventLoopGroup
import io.netty5.channel.socket.DatagramChannel

abstract class AbstractNettyResources : NettyResources {
    override val eventLoopGroup: EventLoopGroup = MultithreadEventLoopGroup(newHandlerFactory())

    abstract fun newDatagramChannel(eventLoop: EventLoop): DatagramChannel

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> newDatagramChannel(eventLoop) }

    override fun close() {
        eventLoopGroup.shutdownGracefully()
    }
}