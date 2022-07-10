package de.localchat.network.netty.udp

import de.localchat.network.netty.BootstrapNetty
import de.localchat.network.netty.resources.NettyResources
import de.localchat.network.util.InterfaceUtil
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import org.tinylog.kotlin.Logger
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import javax.xml.crypto.Data

open class UDPBootstrapNetty : BootstrapNetty<DatagramChannel>() {
    override fun newChannelFactory(): ChannelFactory<DatagramChannel> = resources.newDatagramChannelFactory(InternetProtocolFamily.IPv4)
}