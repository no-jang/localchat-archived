package de.localchat.network.netty.tcp

import de.localchat.network.netty.NettyBootstrap
import io.netty5.channel.ChannelFactory
import io.netty5.channel.socket.SocketChannel
import org.tinylog.kotlin.Logger

class NettyTCPBootstrap(name: String) : NettyBootstrap<SocketChannel>(name) {
    override fun newChannelFactory(): ChannelFactory<SocketChannel> {
        return resources.newSocketChannelFactory()
    }

    fun connect() {
        if (channel != null) return
        remoteAddress ?: throw IllegalStateException("Remote address is null")
        Logger.debug("Connect tcp connection {} to {}:{}", name, remoteAddress, port)

        channel = bootstrap.connect(remoteAddress, port)
            .waitForChannelFuture()
    }
}