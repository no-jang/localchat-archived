package de.localchat.discovery.backend

import de.localchat.discovery.ClientDiscovery
import de.localchat.discovery.DefaultClientDiscovery
import de.localchat.discovery.DiscoveryProtocol.DiscoveryRequest
import de.localchat.network.netty.pipeline.StandardPipelines.defaultProtobuf
import de.localchat.network.netty.udp.NettyMulticastUDPBootstrap
import io.netty5.channel.ChannelHandlerContext
import io.netty5.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Single
import org.tinylog.kotlin.Logger
import java.io.Closeable

@Single(binds = [DiscoveryBackend::class, Closeable::class])
class UDPDiscoveryBackend : DiscoveryBackend, Closeable {
    private val udp = NettyMulticastUDPBootstrap("discovery")

    private val discoveryFlow = MutableSharedFlow<ClientDiscovery>()

    init {
        udp.port = MULTICAST_PORT
        udp.remoteAddress = MULTICAST_ADDRESS
        udp.pipeline = fun(pipeline) {
            pipeline.defaultProtobuf(DiscoveryRequest.getDefaultInstance())
            pipeline.addLast(object : SimpleChannelInboundHandler<DiscoveryRequest>() {
                override fun messageReceived(ctx: ChannelHandlerContext?, msg: DiscoveryRequest) = runBlocking {
                    val discovery = DefaultClientDiscovery(msg.name, msg.address, msg.port)
                    Logger.trace("Received discovery request {}", discovery)
                    discoveryFlow.emit(discovery)
                }
            })
        }

        Logger.debug("Open udp discovery backend")
        udp.bind()
        udp.joinMulticast()
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

    override fun discoveryEvent(): Flow<ClientDiscovery> = discoveryFlow

    override fun close() {
        Logger.debug("Close udp discovery backend")
        udp.close()
    }

    companion object {
        const val MULTICAST_ADDRESS = "224.0.2.60"
        const val MULTICAST_PORT = 4445
    }
}