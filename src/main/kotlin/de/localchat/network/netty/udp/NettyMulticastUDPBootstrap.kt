package de.localchat.network.netty.udp

import de.localchat.network.netty.resources.NettyResources
import de.localchat.network.util.InterfaceUtil
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.InternetProtocolFamily
import org.tinylog.kotlin.Logger
import java.net.InetSocketAddress

abstract class NettyMulticastUDPBootstrap : NettyUDPBootstrap() {
    fun joinMulticast() {
        channel?.let {
            Logger.debug("Channel {} joins multicast group {}", name, remoteAddress)
            val iface = InterfaceUtil.findMulticastIPv4Interface()
                ?: throw IllegalStateException("No IPv4 multicast interface found")
            it.joinGroup(remoteSocketAddress, iface)
        }
    }
}