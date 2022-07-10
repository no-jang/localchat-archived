package de.localchat.discovery.udp

import de.localchat.discovery.Discovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.network.netty.resources.NettyResources
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.DatagramPacket
import io.netty5.channel.socket.InternetProtocolFamily
import io.netty5.channel.socket.nio.NioDatagramChannel
import kotlinx.coroutines.flow.Flow
import java.net.InetAddress
import java.nio.charset.StandardCharsets


class UDPDiscoveryBackend(address: String = MULTICAST_ADDRESS,
                          port: Int = MULTICAST_PORT) : DiscoveryBackend, AutoCloseable {

    private val resources: NettyResources = NettyResources.nativeResources()
    private val channel: DatagramChannel

    class ServerMulticastHandler : SimpleChannelInboundHandler<DatagramPacket>() {
        override fun messageReceived(ctx: ChannelHandlerContext?, msg: DatagramPacket?) {
            if(msg == null) return
            println(msg.content().toString(StandardCharsets.UTF_8))
        }
    }

    init {
        val groupAddress = InetAddress.getByName(address)

        val bootstrap = Bootstrap()
            .group(resources.eventLoopGroup)
            .channelFactory(resources.newDatagramChannelFactory())
            .option(ChannelOption.SO_REUSEADDR, true)
            .handler(object : ChannelInitializer<DatagramChannel>() {
                override fun initChannel(ch: DatagramChannel) {
                    ch.pipeline().addLast(ServerMulticastHandler())
                }
            })

        channel = bootstrap
            .bind(port)
            .asStage()
            .get() as DatagramChannel

        channel
            .joinGroup(groupAddress)
            .asStage()
            .sync()

        resources.close()
    }

    override fun send(discovery: Discovery) {
        channel.writeAndFlush(discovery.toString())
    }

    override fun discovered(): Flow<Discovery> {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    companion object {
        const val MULTICAST_ADDRESS = "224.0.2.60"
        const val MULTICAST_PORT = 4445
    }
}