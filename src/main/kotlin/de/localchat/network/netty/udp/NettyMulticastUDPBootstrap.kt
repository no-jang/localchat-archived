package de.localchat.network.netty.udp

import de.localchat.network.util.InterfaceUtil
import org.tinylog.kotlin.Logger

class NettyMulticastUDPBootstrap(name: String) : NettyUDPBootstrap(name) {
    fun joinMulticast() {
        channel?.let {
            Logger.debug("Channel {} joins multicast group {}", name, remoteAddress)
            val iface = InterfaceUtil.findMulticastIPv4Interface()
                ?: throw IllegalStateException("No IPv4 multicast interface found")
            it.joinGroup(remoteSocketAddress, iface)
        }
    }
}