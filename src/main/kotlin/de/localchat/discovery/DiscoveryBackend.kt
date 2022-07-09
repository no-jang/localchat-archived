package de.localchat.discovery

import kotlinx.coroutines.flow.Flow

interface DiscoveryBackend {
    fun send(discovery: Discovery)
    fun discover(): Flow<Discovery>
    fun terminate()
}