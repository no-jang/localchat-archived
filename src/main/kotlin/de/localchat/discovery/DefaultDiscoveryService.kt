package de.localchat.discovery

//import de.localchat.discovery.backend.DiscoveryBackend
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