package de.localchat.discovery.common

import de.localchat.discovery.ClientDiscovery

data class DefaultClientDiscovery(override val host: String, override val port: Int) : ClientDiscovery