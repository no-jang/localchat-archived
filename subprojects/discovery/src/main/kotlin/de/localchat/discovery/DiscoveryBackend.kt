package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend {
    fun open()
    fun close()

    fun broadcastRequest(request: ClientDiscovery)
    fun receivedRequest(): Flow<ClientDiscovery>
}