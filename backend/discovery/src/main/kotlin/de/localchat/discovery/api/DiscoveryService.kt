package de.localchat.discovery.api

import kotlinx.coroutines.flow.Flow

interface DiscoveryService {
    fun registerService(service: ServiceInfo)

    fun onServiceDiscovery(service: ServiceInfo): Flow<ServiceEventInfo>

    fun unregisterService(service: ServiceInfo)

    fun destroy()
}