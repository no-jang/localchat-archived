package de.localchat.network.netty.resources

import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.kqueue.KQueueDatagramChannel
import io.netty5.channel.kqueue.KQueueHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily

class KQueueNettyResources : NettyResources {
    override val type: NettyResources.Type = NettyResources.Type.KQUEUE

    override fun newHandlerFactory(): IoHandlerFactory =
        KQueueHandler.newFactory()

    override fun newDatagramChannelFactory(protocolFamily: InternetProtocolFamily): ChannelFactory<DatagramChannel> =
        ChannelFactory { eventLoop -> KQueueDatagramChannel(eventLoop, protocolFamily) }
}