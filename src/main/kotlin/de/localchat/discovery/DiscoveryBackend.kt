package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend {
    fun send(discovery: ClientDiscovery)

    fun discoveryEvent(): Flow<ClientDiscovery>
}