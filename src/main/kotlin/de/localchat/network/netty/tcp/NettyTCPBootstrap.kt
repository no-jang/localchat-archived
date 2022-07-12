package de.localchat.network.netty.tcp

import de.localchat.network.netty.NettyBootstrap
import io.netty5.channel.ChannelFactory
import io.netty5.channel.socket.SocketChannel
import org.tinylog.kotlin.Logger

abstract class NettyTCPBootstrap : NettyBootstrap<SocketChannel>() {
    override fun newChannelFactory(): ChannelFactory<SocketChannel> {
        return resources.newSocketChannelFactory()
    }

    fun connect() {
        if(channel != null) return
        Logger.debug("Connect tcp connection {} to {}:{}", name, remoteAddress, port)

        channel = bootstrap.connect(remoteAddress, port)
            .waitForChannelFuture()
    }
}