package de.localchat.network.netty

import de.localchat.network.netty.resources.NettyResources
import io.netty5.bootstrap.AbstractBootstrap
import io.netty5.channel.*
import io.netty5.util.concurrent.Future
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress

typealias PipelineCallback = (pipeline: ChannelPipeline) -> Unit

abstract class NettyAbstractBootstrap<B : AbstractBootstrap<*, *, *>, C : Channel>(
    val name: String
) : AutoCloseable {

    protected val resources: NettyResources = NettyResources.nativeResources()
    protected val eventLoopGroup: EventLoopGroup = MultithreadEventLoopGroup(resources.newHandlerFactory())

    protected val remoteSocketAddress: InetSocketAddress? by lazy {
        if (remoteAddress == null) return@lazy null
        InetSocketAddress(remoteAddress, port)
    }

    @Suppress("UNCHECKED_CAST")
    protected val bootstrap: B = newBootstrap()
        .option(ChannelOption.SO_REUSEADDR, true)
        .group(eventLoopGroup)
        .handler(object : ChannelInitializer<C>() {
            override fun initChannel(ch: C) {
                pipeline?.invoke(ch.pipeline())
            }
        }) as B

    protected var channel: C? = null

    open var port: Int = 0
        set(value) {
            if (channel != null) return
            field = value
        }

    open var remoteAddress: String? = null
        set(value) {
            if (channel != null) return
            field = value
        }

    open var pipeline: PipelineCallback? = null
        set(value) {
            if (channel != null) return
            field = value
        }

    abstract fun newBootstrap(): B

    open fun send(o: Any) {
        channel?.writeAndFlush(o)
    }

    override fun close() {
        Logger.debug("Close {} from port: {}, remote address: {}", name, port, remoteAddress)
        channel?.close()
        eventLoopGroup.shutdownGracefully()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun Future<Channel>.waitForChannelFuture(): C =
        asStage()
            .sync()
            .get() as C
}