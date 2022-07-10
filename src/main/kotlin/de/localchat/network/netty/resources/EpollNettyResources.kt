package de.localchat.network.netty.resources

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.epoll.EpollDatagramChannel
import io.netty5.channel.epoll.EpollHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily

class EpollNettyResources : NettyResources {
    override val type: NettyResources.Type = NettyResources.Type.EPOLL

    override fun newHandlerFactory(): IoHandlerFactory =
        EpollHandler.newFactory()

    override fun newDatagramChannelFactory(protocolFamily: InternetProtocolFamily): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> EpollDatagramChannel(eventLoop, protocolFamily) }
}