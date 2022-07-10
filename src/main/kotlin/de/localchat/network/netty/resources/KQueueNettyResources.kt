package de.localchat.network.netty.resources

import io.netty5.channel.EventLoop
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.kqueue.KQueueDatagramChannel
import io.netty5.channel.kqueue.KQueueHandler
import io.netty5.channel.socket.DatagramChannel

class KQueueNettyResources : AbstractNettyResources() {
    override fun newHandlerFactory(): IoHandlerFactory =
        KQueueHandler.newFactory()

    override fun newDatagramChannel(eventLoop: EventLoop): DatagramChannel =
        KQueueDatagramChannel(eventLoop)
}