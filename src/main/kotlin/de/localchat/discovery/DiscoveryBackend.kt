package de.localchat.discovery

import de.localchat.util.lifecycle.Lifecycle
import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend : Lifecycle {
    fun send(discovery: ClientDiscovery)

    fun discoveryEvent(): Flow<ClientDiscovery>
}