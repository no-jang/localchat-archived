package de.localchat.discovery

interface DiscoveryService {
    fun acknowledgeDiscovery(discovery: ClientDiscovery)
}