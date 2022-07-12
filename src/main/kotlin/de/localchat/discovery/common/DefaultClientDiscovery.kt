package de.localchat.discovery.common

import de.localchat.discovery.ClientDiscovery

data class DefaultClientDiscovery(override val name: String, override val address: String, override val port: Int) :
    ClientDiscovery