package de.localchat.network.netty.udp

import de.localchat.network.util.InterfaceUtil
import org.tinylog.kotlin.Logger
import java.io.IOException
import java.net.InetSocketAddress

class MulticastUDPBootstrapNetty(multicastAdress: String, private val port: Int) : UDPBootstrapNetty() {
    private val groupAddress: InetSocketAddress = InetSocketAddress(multicastAdress, port)

    fun bindAndJoin() {
        bind(port)
        joinGroup()
    }

    fun joinGroup() {
        val iface = InterfaceUtil.findMulticastIPv4Interface()
            ?: throw IOException("No IPv4 multicast interface found")

        Logger.debug("Join udp multicast on interface {} {}", iface.displayName, groupAddress)

        channel
            ?.joinGroup(groupAddress, iface)
            ?.asStage()
            ?.sync()
    }

}