package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryService {
    fun acknowledgeDiscovery(discovery: ClientDiscovery)

    fun discoveryEvent(): Flow<ClientDiscovery>
}