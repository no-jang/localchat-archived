package de.localchat.network.netty.resources

import io.netty5.channel.Channel
import io.netty5.channel.ChannelFactory
import io.netty5.channel.EventLoop
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.epoll.EpollDatagramChannel
import io.netty5.channel.epoll.EpollHandler
import io.netty5.channel.socket.DatagramChannel

class EpollNettyResources : AbstractNettyResources() {
    override fun newHandlerFactory(): IoHandlerFactory =
        EpollHandler.newFactory()

    override fun newDatagramChannel(eventLoop: EventLoop): DatagramChannel =
        EpollDatagramChannel(eventLoop)
}