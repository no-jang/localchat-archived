package de.localchat.network.library.netty.environment

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.epoll.EpollDatagramChannel
import io.netty5.channel.epoll.EpollHandler
import io.netty5.channel.epoll.EpollServerSocketChannel
import io.netty5.channel.epoll.EpollSocketChannel
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

class EpollNettyEnvironment : NettyEnvironment {
    override fun getName(): String = "epoll"

    override fun newHandlerFactory(): IoHandlerFactory =
        EpollHandler.newFactory()

    override fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> EpollDatagramChannel(eventLoop) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> EpollSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> EpollServerSocketChannel(eventLoop, eventLoopGroup) }
}