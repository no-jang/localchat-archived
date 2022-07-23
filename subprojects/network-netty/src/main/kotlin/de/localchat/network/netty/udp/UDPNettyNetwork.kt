package de.localchat.network.netty.udp

import de.localchat.network.netty.resources.NettyResources
import de.localchat.network.netty.resources.NettyResourcesUtil
import de.localchat.network.netty.util.waitForFuture
import de.localchat.network.udp.UDPConnection
import de.localchat.network.udp.UDPNetwork
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.ChannelInitializer
import io.netty5.channel.ChannelOption
import io.netty5.channel.EventLoopGroup
import io.netty5.channel.MultithreadEventLoopGroup
import io.netty5.channel.socket.DatagramChannel
import org.tinylog.kotlin.Logger

class UDPNettyNetwork(val name: String) : UDPNetwork {
    override var port: Int? = null

    private val resources: NettyResources
    private val eventLoopGroup: EventLoopGroup
    private val bootstrap: Bootstrap

    private val connections = mutableListOf<UDPConnection>()

    init {
        val resourceType = NettyResourcesUtil.supportedType()

        Logger.info("Initializing udp network {} with resource type: {}", name, resourceType)

        resources = NettyResourcesUtil.newResources(resourceType)
        eventLoopGroup = MultithreadEventLoopGroup(resources.newHandlerFactory())

        bootstrap = Bootstrap()
            .option(ChannelOption.SO_REUSEADDR, true)
            .group(eventLoopGroup)
            .channelFactory(resources.newDatagramChannelFactory())
            .handler(object : ChannelInitializer<DatagramChannel>() {
                override fun initChannel(ch: DatagramChannel) {
                    // TODO Add pipeline
                }
            })
    }

    override fun bind(name: String): UDPConnection {
        val _port = port ?: throw IllegalStateException("Port is not set")

        Logger.debug("Bind udp network: {}, connection: {}, port: {}", this.name, name, _port)

        val channel = bootstrap.bind().waitForFuture() as DatagramChannel

        val connection = UDPNettyConnection(name, _port, this, channel)
        connections.add(connection)

        return connection
    }

    override fun close() {
        Logger.debug("Close udp network: {}", name)

        connections.forEach {
            it.close()
        }

        eventLoopGroup.shutdownGracefully()
    }

    internal fun connectionClosed(connection: UDPConnection) {
        connections.remove(connection)
    }
}