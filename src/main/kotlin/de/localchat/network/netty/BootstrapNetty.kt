package de.localchat.network.netty

import de.localchat.network.netty.resources.NettyResources
import de.localchat.network.netty.udp.UDPBootstrapNetty
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import org.tinylog.kotlin.Logger

abstract class BootstrapNetty<C : Channel> : AutoCloseable {
    protected val resources: NettyResources = NettyResources.nativeBackend()
    protected val eventLoopGroup: EventLoopGroup = MultithreadEventLoopGroup(resources.newHandlerFactory())

    protected val bootstrap: Bootstrap = Bootstrap()
        .option(ChannelOption.SO_REUSEADDR, true)
        .group(eventLoopGroup)
        .channelFactory(newChannelFactory())

    protected var channel: C? = null
        private set

    abstract fun newChannelFactory(): ChannelFactory<C>

    fun handler(handler: ChannelHandler) {
        bootstrap.handler(handler)
    }

    fun bind(port: Int) = channel?.let {
        Logger.debug("Bind server on port {}", port)

        channel = bootstrap
            .bind(port)
            .asStage()
            .sync()
            .get() as C
    }

    fun send(o: Any) {
        channel?.writeAndFlush(o)
    }

    override fun close() {
        Logger.debug("Close server")
        eventLoopGroup.shutdownGracefully()
    }
}