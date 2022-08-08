package de.localchat.network.library.netty.environment

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.kqueue.KQueueDatagramChannel
import io.netty5.channel.kqueue.KQueueHandler
import io.netty5.channel.kqueue.KQueueServerSocketChannel
import io.netty5.channel.kqueue.KQueueSocketChannel
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

class KQueueNettyEnvironment : NettyEnvironment {
    override fun getName(): String = "kqueue"

    override fun newHandlerFactory(): IoHandlerFactory =
        KQueueHandler.newFactory()

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> KQueueDatagramChannel(eventLoop) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> KQueueSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> KQueueServerSocketChannel(eventLoop, eventLoopGroup) }
}