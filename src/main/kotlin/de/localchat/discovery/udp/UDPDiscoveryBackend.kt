package de.localchat.discovery.udp

import de.localchat.discovery.DiscoveryProtocol
import de.localchat.discovery.Discovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.network.netty.udp.UDPBootstrapNetty
import io.netty.contrib.handler.codec.protobuf.ProtobufDecoder
import io.netty.contrib.handler.codec.protobuf.ProtobufEncoder
import io.netty.contrib.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.contrib.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import io.netty5.channel.ChannelHandlerContext
import io.netty5.channel.ChannelInitializer
import io.netty5.channel.DefaultAddressedEnvelope
import io.netty5.channel.SimpleChannelInboundHandler
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.DatagramPacket
import io.netty5.handler.codec.DatagramPacketDecoder
import io.netty5.handler.codec.DatagramPacketEncoder
import io.netty5.handler.codec.DelimiterBasedFrameDecoder
import io.netty5.handler.codec.Delimiters
import io.netty5.handler.codec.LengthFieldBasedFrameDecoder
import io.netty5.handler.codec.LengthFieldPrepender
import io.netty5.handler.codec.string.StringDecoder
import io.netty5.handler.codec.string.StringEncoder
import io.netty5.handler.logging.LogLevel
import io.netty5.handler.logging.LoggingHandler
import kotlinx.coroutines.flow.Flow
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress


class UDPDiscoveryBackend(
    address: String = MULTICAST_ADDRESS,
    port: Int = MULTICAST_PORT
) : DiscoveryBackend, UDPBootstrapNetty() {
    class ServerMulticastHandler : SimpleChannelInboundHandler<DiscoveryProtocol.DiscoveryRequest>() {
        override fun messageReceived(ctx: ChannelHandlerContext?, msg: DiscoveryProtocol.DiscoveryRequest?) {
            println(msg.toString())
        }
    }

    init {
        val groupAddress = InetSocketAddress(address, port)

        handler(object : ChannelInitializer<DatagramChannel>() {
            override fun initChannel(ch: DatagramChannel) {
                val pipeline = ch.pipeline()

                pipeline.addLast(LoggingHandler(LogLevel.DEBUG))
                //pipeline.addLast(LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4))
                //pipeline.addLast(ProtobufVarint32FrameDecoder())
                //pipeline.addLast(ProtobufDecoder(DiscoveryProtocol.DiscoveryRequest.getDefaultInstance()))
                pipeline.addLast(DatagramPacketDecoder(ProtobufDecoder(DiscoveryProtocol.DiscoveryRequest.getDefaultInstance())))

                //pipeline.addLast(LengthFieldPrepender(4))
                //pipeline.addLast(ProtobufVarint32LengthFieldPrepender())
                //pipeline.addLast(ProtobufEncoder())
                pipeline.addLast(DatagramPacketEncoder(ProtobufEncoder()))

                pipeline.addLast(ServerMulticastHandler())
            }
        })

        Logger.debug("Start udp discovery backend")

        bind(groupAddress.port)
        joinGroup(groupAddress)
    }

    override fun send(discovery: Discovery) {
        val discoveryRequest = DiscoveryProtocol.DiscoveryRequest.newBuilder()
            .setHost(discovery.host)
            .setPort(discovery.port)
            .build()

        send(DefaultAddressedEnvelope(discoveryRequest, InetSocketAddress(MULTICAST_ADDRESS, MULTICAST_PORT)))
        //send(discoveryRequest)
    }

    override fun discovered(): Flow<Discovery> {
        TODO("Not yet implemented")
    }

    companion object {
        const val MULTICAST_ADDRESS = "224.0.2.60"
        const val MULTICAST_PORT = 4445
    }
}