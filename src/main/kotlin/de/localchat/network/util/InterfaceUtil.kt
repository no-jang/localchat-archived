package de.localchat.network.util

import java.net.NetworkInterface

object InterfaceUtil {
    fun findMulticastIPv4Interface(): NetworkInterface? {
        return NetworkInterface.getNetworkInterfaces().toList()
            .find { iface -> iface.isUp && iface.supportsMulticast() }
    }
}