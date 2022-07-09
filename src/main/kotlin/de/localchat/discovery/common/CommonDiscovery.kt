package de.localchat.discovery.common

import de.localchat.discovery.Discovery

data class CommonDiscovery(override val host: String, override val port: Int) : Discovery {
}