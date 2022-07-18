package de.localchat.discovery

data class DefaultClientDiscovery(override val name: String, override val address: String, override val port: Int) :
    ClientDiscovery