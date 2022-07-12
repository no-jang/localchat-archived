package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryService {
    fun discoveryEvent(): Flow<ClientDiscovery>
}