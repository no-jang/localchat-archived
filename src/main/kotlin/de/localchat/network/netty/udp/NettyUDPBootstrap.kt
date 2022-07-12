package de.localchat.network.netty.udp

import de.localchat.network.netty.resources.NettyResources
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress

typealias PipelineCallback = (pipeline: ChannelPipeline) -> Unit

abstract class NettyUDPBootstrap : AutoCloseable {
    abstract val name: String
    abstract val port: Int
    abstract val remoteAddress: String
    abstract val pipeline: PipelineCallback

    protected val resources: NettyResources = NettyResources.nativeResources()
    protected val eventLoopGroup: EventLoopGroup = MultithreadEventLoopGroup(resources.newHandlerFactory())

    protected val remoteSocketAddress: InetSocketAddress by lazy {
        InetSocketAddress(remoteAddress, port)
    }

    protected val bootstrap: Bootstrap by lazy {
        Bootstrap()
            .option(ChannelOption.SO_REUSEADDR, true)
            .localAddress(remoteAddress, port)
            .group(eventLoopGroup)
            .channelFactory(resources.newDatagramChannelFactory(InternetProtocolFamily.IPv4))
            .handler(object : ChannelInitializer<DatagramChannel>() {
                override fun initChannel(ch: DatagramChannel) {
                    pipeline.invoke(ch.pipeline())
                }
            })
    }

    protected var channel: DatagramChannel? = null

    fun bind() {
        if(channel != null) return
        Logger.debug("Bind {} to {}:{}", name, remoteAddress, port)
        channel = bootstrap
            .bind()
            .asStage()
            .sync()
            .get() as DatagramChannel
    }

    fun send(o: Any) {
        channel?.writeAndFlush(DefaultAddressedEnvelope(o, remoteSocketAddress))
    }

    override fun close() {
        Logger.debug("Close {} {}:{}", name, remoteAddress, port)
        channel?.close()
        eventLoopGroup.shutdownGracefully()
    }
}