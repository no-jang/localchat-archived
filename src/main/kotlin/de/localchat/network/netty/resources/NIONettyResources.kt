package de.localchat.network.netty.resources

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.nio.NioHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import io.netty5.channel.socket.nio.NioDatagramChannel

class NIONettyResources : NettyResources {
    override val type: NettyResources.Type = NettyResources.Type.NIO

    override fun newHandlerFactory(): IoHandlerFactory =
        NioHandler.newFactory()

    override fun newDatagramChannelFactory(protocolFamily: InternetProtocolFamily): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> NioDatagramChannel(eventLoop, protocolFamily) }
}