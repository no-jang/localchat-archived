package de.localchat.network.netty.resources

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.kqueue.KQueueDatagramChannel
import io.netty5.channel.kqueue.KQueueHandler
import io.netty5.channel.kqueue.KQueueServerSocketChannel
import io.netty5.channel.kqueue.KQueueSocketChannel
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

class KQueueNettyResources : NettyResources {
    override val type: NettyResources.Type = NettyResources.Type.KQUEUE

    override fun newHandlerFactory(): IoHandlerFactory =
        KQueueHandler.newFactory()

    override fun newDatagramChannelFactory(protocolFamily: InternetProtocolFamily): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> KQueueDatagramChannel(eventLoop, protocolFamily) }

    override fun newSocketChannelFactory(): ChannelFactory<SocketChannel> =
        ChannelFactory { eventLoop -> KQueueSocketChannel(eventLoop) }

    override fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel> =
        ServerChannelFactory { eventLoop, eventLoopGroup -> KQueueServerSocketChannel(eventLoop, eventLoopGroup) }
}