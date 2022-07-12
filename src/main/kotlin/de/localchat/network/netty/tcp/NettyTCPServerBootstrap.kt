package de.localchat.network.netty.tcp

import de.localchat.network.netty.NettyServerBootstrap
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.socket.ServerSocketChannel
import org.tinylog.kotlin.Logger

abstract class NettyTCPServerBootstrap : NettyServerBootstrap<ServerSocketChannel>() {
    override fun newServerChannelFactory(): ServerChannelFactory<ServerSocketChannel> {
        return resources.newServerSocketChannelFactory()
    }

    fun bind() {
        if (channel != null) return
        Logger.debug("Bind tcp connection {} to port {}", name, port)

        channel = bootstrap.bind(port)
            .waitForChannelFuture()
    }
}