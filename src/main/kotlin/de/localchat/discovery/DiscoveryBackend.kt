package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend : AutoCloseable {
    suspend fun open(): Flow<ClientDiscovery>

    fun send(discovery: ClientDiscovery)
}