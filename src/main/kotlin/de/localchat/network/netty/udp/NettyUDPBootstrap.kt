package de.localchat.network.netty.udp

import de.localchat.network.netty.NettyBootstrap
import io.netty5.channel.ChannelFactory
import io.netty5.channel.ChannelPipeline
import io.netty5.channel.DefaultAddressedEnvelope
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import java.net.InetSocketAddress

typealias PipelineCallback = (pipeline: ChannelPipeline) -> Unit

abstract class NettyUDPBootstrap : NettyBootstrap<DatagramChannel>() {
    override fun newChannelFactory(): ChannelFactory<DatagramChannel> {
        return resources.newDatagramChannelFactory(InternetProtocolFamily.IPv4)
    }

    override fun send(o: Any) {
        send(o, remoteSocketAddress!!)
    }

    fun send(o: Any, remoteAddress: String, port: Int) {
        send(o, InetSocketAddress(remoteAddress, port))
    }

    fun send(o: Any, remoteAddress: InetSocketAddress) {
        super.send(DefaultAddressedEnvelope(o, remoteAddress))
    }
}