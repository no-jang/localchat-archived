package de.localchat.discovery.api.strategy

import de.localchat.discovery.api.ServiceEventInfo
import de.localchat.discovery.api.ServiceInfo
import kotlinx.coroutines.flow.Flow

interface DiscoveryStrategy {
    fun registerService(service: ServiceInfo)

    fun onServiceDiscovery(service: ServiceInfo): Flow<ServiceEventInfo>
    fun onServiceDisappearance(service: ServiceInfo): Flow<ServiceEventInfo>

    fun unregisterService(service: ServiceInfo)

    fun destroy()
}