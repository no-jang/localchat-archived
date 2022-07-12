package de.localchat.discovery.udp

import de.localchat.discovery.ClientDiscovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.discovery.DiscoveryProtocol.DiscoveryRequest
import de.localchat.discovery.common.DefaultClientDiscovery
import de.localchat.network.netty.PipelineCallback
import de.localchat.network.netty.udp.NettyMulticastUDPBootstrap
import io.netty.contrib.handler.codec.protobuf.ProtobufDecoder
import io.netty.contrib.handler.codec.protobuf.ProtobufEncoder
import io.netty5.channel.ChannelHandlerContext
import io.netty5.channel.ChannelPipeline
import io.netty5.channel.SimpleChannelInboundHandler
import io.netty5.handler.codec.DatagramPacketDecoder
import io.netty5.handler.codec.DatagramPacketEncoder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.runBlocking
import org.tinylog.kotlin.Logger

class UDPDiscoveryBackend : NettyMulticastUDPBootstrap(), DiscoveryBackend {
    override val name: String = "discovery"
    override val port: Int = MULTICAST_PORT
    override val remoteAddress: String = MULTICAST_ADDRESS
    override val pipeline: PipelineCallback = fun(pipeline: ChannelPipeline) {
        //pipeline.addLast(LoggingHandler(LogLevel.INFO))
        pipeline.addLast(DatagramPacketDecoder(ProtobufDecoder(DiscoveryRequest.getDefaultInstance())))
        pipeline.addLast(DatagramPacketEncoder(ProtobufEncoder()))
        pipeline.addLast(DiscoveryHandler(discoveredChannel))
    }

    private val discoveredChannel: Channel<ClientDiscovery> = Channel()

    override fun open() {
        Logger.debug("Start UDP discovery")
        bind()
        joinMulticast()
    }

    override fun send(discovery: ClientDiscovery) {
        send(
            DiscoveryRequest.newBuilder()
                .setName(discovery.name)
                .setAddress(discovery.address)
                .setPort(discovery.port)
        )
    }

    override fun discovered(): Flow<ClientDiscovery> {
        return discoveredChannel.consumeAsFlow()
    }

    companion object {
        const val MULTICAST_ADDRESS = "224.0.2.60"
        const val MULTICAST_PORT = 4445
    }

    private class DiscoveryHandler(val discoveredChannel: Channel<ClientDiscovery>) :
        SimpleChannelInboundHandler<DiscoveryRequest>() {
        override fun messageReceived(ctx: ChannelHandlerContext?, msg: DiscoveryRequest) = runBlocking {
            val discovery = DefaultClientDiscovery(msg.name, msg.address, msg.port)
            discoveredChannel.send(discovery)
        }
    }
}