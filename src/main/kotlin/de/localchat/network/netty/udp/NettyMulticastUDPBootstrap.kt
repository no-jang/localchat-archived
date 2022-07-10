package de.localchat.network.netty.udp

import de.localchat.network.netty.resources.NettyResources
import de.localchat.network.util.InterfaceUtil
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress

typealias PipelineCallback = (pipeline: ChannelPipeline) -> Unit

abstract class NettyMulticastUDPBootstrap : AutoCloseable {
    abstract val name: String
    abstract val groupAddress: String
    abstract val port: Int
    abstract val pipeline: PipelineCallback

    private val resources: NettyResources = NettyResources.nativeResources()
    private val eventLoopGroup: EventLoopGroup = MultithreadEventLoopGroup(resources.newHandlerFactory())

    private val groupSocketAddress: InetSocketAddress by lazy {
        InetSocketAddress(groupAddress, port)
    }

    private val bootstrap = Bootstrap()
        .option(ChannelOption.SO_REUSEADDR, true)
        .group(eventLoopGroup)
        .channelFactory(resources.newDatagramChannelFactory(InternetProtocolFamily.IPv4))
        .handler(object : ChannelInitializer<DatagramChannel>() {
            override fun initChannel(ch: DatagramChannel) {
                pipeline.invoke(ch.pipeline())
            }
        })

    private var channel: DatagramChannel? = null

    fun bind() {
        if (channel != null) return
        Logger.debug("Bind {} to {}:{}", name, groupAddress, port)
        channel = bootstrap
            .bind(port)
            .asStage()
            .sync()
            .get() as DatagramChannel
    }

    fun joinGroup() {
        channel?.let {
            Logger.debug("Channel {} joins multicast group {}", name, groupAddress)
            val iface = InterfaceUtil.findMulticastIPv4Interface()
                ?: throw IllegalStateException("No IPv4 multicast interface found")
            it.joinGroup(groupSocketAddress, iface)
        }
    }

    fun bindAndJoin() {
        bind()
        joinGroup()
    }

    fun send(o: Any) {
        channel?.writeAndFlush(DefaultAddressedEnvelope(o, groupSocketAddress))
    }

    override fun close() {
        Logger.debug("Close {}", name)
        channel?.close()
        eventLoopGroup.shutdownGracefully()
    }
}