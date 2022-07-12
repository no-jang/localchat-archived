package de.localchat.discovery.udp

import de.localchat.discovery.ClientDiscovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.discovery.DiscoveryProtocol.DiscoveryRequest
import de.localchat.discovery.common.DefaultClientDiscovery
import de.localchat.network.netty.udp.NettyMulticastUDPBootstrap
import de.localchat.network.netty.PipelineCallback
import io.netty.contrib.handler.codec.protobuf.ProtobufDecoder
import io.netty.contrib.handler.codec.protobuf.ProtobufEncoder
import io.netty5.channel.ChannelHandlerContext
import io.netty5.channel.ChannelPipeline
import io.netty5.channel.SimpleChannelInboundHandler
import io.netty5.handler.codec.DatagramPacketDecoder
import io.netty5.handler.codec.DatagramPacketEncoder
import io.netty5.handler.logging.LogLevel
import io.netty5.handler.logging.LoggingHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import org.tinylog.kotlin.Logger

class UDPDiscoveryBackend(
    override val remoteAddress: String = MULTICAST_ADDRESS,
    override val port: Int = MULTICAST_PORT
) : NettyMulticastUDPBootstrap(), DiscoveryBackend {

    private class DiscoveryHandler(val backend: UDPDiscoveryBackend) : SimpleChannelInboundHandler<DiscoveryRequest>() {
        override fun messageReceived(ctx: ChannelHandlerContext?, msg: DiscoveryRequest) = runBlocking {
            val discovery = DefaultClientDiscovery(msg.host, msg.port)
            backend.discoveredChannel.send(discovery)
        }
    }

    override val name: String
        get() = "udp discovery"

    private val discoveredChannel: Channel<ClientDiscovery> = Channel()

    override val pipeline: PipelineCallback = fun(pipeline: ChannelPipeline) {
        pipeline.addLast(LoggingHandler(LogLevel.DEBUG))
        pipeline.addLast(DatagramPacketDecoder(ProtobufDecoder(DiscoveryRequest.getDefaultInstance())))
        pipeline.addLast(DatagramPacketEncoder(ProtobufEncoder()))
        pipeline.addLast(DiscoveryHandler(this))
    }

    init {
        Logger.debug("Start udp discovery")
        bind()
        joinMulticast()
    }

    override fun send(discovery: ClientDiscovery) {
        send(
            DiscoveryRequest.newBuilder()
                .setHost(discovery.host)
                .setPort(discovery.port)
                .build()
        )
    }

    override fun discovered(): Flow<ClientDiscovery> = discoveredChannel.receiveAsFlow()

    companion object {
        const val MULTICAST_ADDRESS = "224.0.2.60"
        const val MULTICAST_PORT = 4445
    }
}