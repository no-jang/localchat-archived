package de.localchat.discovery.backend

import de.localchat.discovery.ClientDiscovery
import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend {
    fun send(discovery: ClientDiscovery)

    fun discoveryEvent(): Flow<ClientDiscovery>
}