package de.localchat.network.netty.udp

import de.localchat.network.netty.NettyBootstrap
import io.netty5.channel.ChannelFactory
import io.netty5.channel.DefaultAddressedEnvelope
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress

open class NettyUDPBootstrap(name: String) : NettyBootstrap<DatagramChannel>(name) {
    override fun newChannelFactory(): ChannelFactory<DatagramChannel> {
        return resources.newDatagramChannelFactory(InternetProtocolFamily.IPv4)
    }

    fun bind() {
        if (channel != null) return
        Logger.debug("Bind udp connection {} to port {}", name, port)

        channel = bootstrap.bind(port)
            .waitForChannelFuture()
    }

    override fun send(o: Any) {
        send(o, remoteSocketAddress ?: throw IllegalStateException("Remote address is null"))
    }

    fun send(o: Any, remoteAddress: String, port: Int) {
        send(o, InetSocketAddress(remoteAddress, port))
    }

    fun send(o: Any, remoteAddress: InetSocketAddress) {
        super.send(DefaultAddressedEnvelope(o, remoteAddress))
    }
}