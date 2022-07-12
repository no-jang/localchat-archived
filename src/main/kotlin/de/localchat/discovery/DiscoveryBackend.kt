package de.localchat.discovery

import de.localchat.util.lifecycle.LifeCycle
import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend : LifeCycle {
    fun send(discovery: ClientDiscovery)

    fun discoveryEvent(): Flow<ClientDiscovery>
}