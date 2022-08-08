package de.localchat.network.library.netty.environment

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.nio.NioHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel
import io.netty5.channel.socket.nio.NioDatagramChannel
import io.netty5.channel.socket.nio.NioServerSocketChannel
import io.netty5.channel.socket.nio.NioSocketChannel

class NIONettyEnvironment : NettyEnvironment {
    override fun getName(): String = "nio"

    override fun newHandlerFactory(): IoHandlerFactory =
        NioHandler.newFactory()

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> NioDatagramChannel(eventLoop) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> NioSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> NioServerSocketChannel(eventLoop, eventLoopGroup) }
}