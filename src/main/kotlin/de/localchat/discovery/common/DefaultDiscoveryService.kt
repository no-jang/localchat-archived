package de.localchat.discovery.common

import de.localchat.discovery.ClientDiscovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.discovery.DiscoveryService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class DefaultDiscoveryService(val backend: DiscoveryBackend) : DiscoveryService {
    init {
        runBlocking {

        }
    }

    override fun discoveryEvent(): Flow<ClientDiscovery> {
        TODO("Not yet implemented")
    }
}