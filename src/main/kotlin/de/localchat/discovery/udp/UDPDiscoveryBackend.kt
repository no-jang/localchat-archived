package de.localchat.discovery.udp

import de.localchat.discovery.ClientDiscovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.discovery.DiscoveryProtocol.DiscoveryRequest
import de.localchat.discovery.common.DefaultClientDiscovery
import de.localchat.network.netty.pipeline.StandardPipelines.defaultProtobuf
import de.localchat.network.netty.udp.NettyMulticastUDPBootstrap
import io.netty5.channel.ChannelHandlerContext
import io.netty5.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.runBlocking
import org.tinylog.kotlin.Logger

class UDPDiscoveryBackend : DiscoveryBackend {
    private val udp = NettyMulticastUDPBootstrap("discovery")

    init {
        udp.port = MULTICAST_PORT
        udp.remoteAddress = MULTICAST_ADDRESS
    }

    override suspend fun open(): Flow<ClientDiscovery> {
        val discoveryChannel = Channel<ClientDiscovery>()

        udp.pipeline = fun(pipeline) {
            pipeline.defaultProtobuf(DiscoveryRequest.getDefaultInstance())
            pipeline.addLast(object : SimpleChannelInboundHandler<DiscoveryRequest>() {
                override fun messageReceived(ctx: ChannelHandlerContext?, msg: DiscoveryRequest) = runBlocking {
                    val discovery = DefaultClientDiscovery(msg.name, msg.address, msg.port)
                    Logger.trace("Received discovery request {}", discovery)
                    discoveryChannel.send(discovery)
                }
            })
        }

        Logger.debug("Open udp discovery backend")
        udp.bind()
        udp.joinMulticast()

        return discoveryChannel.consumeAsFlow()
    }

    override fun send(discovery: ClientDiscovery) {
        Logger.trace("Send discovery request {}", discovery)
        udp.send(
            DiscoveryRequest.newBuilder()
                .setName(discovery.name)
                .setAddress(discovery.address)
                .setPort(discovery.port)
        )
    }

    override fun close() {
        Logger.debug("Close udp discovery backend")
        udp.close()
    }

    companion object {
        const val MULTICAST_ADDRESS = "224.0.2.60"
        const val MULTICAST_PORT = 4445
    }
}