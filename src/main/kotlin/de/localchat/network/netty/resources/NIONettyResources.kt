package de.localchat.network.netty.resources

import io.netty5.channel.EventLoop
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.nio.NioHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.nio.NioDatagramChannel

class NIONettyResources : AbstractNettyResources() {
    override fun newHandlerFactory(): IoHandlerFactory =
        NioHandler.newFactory()

    override fun newDatagramChannel(eventLoop: EventLoop): DatagramChannel =
        NioDatagramChannel(eventLoop)
}