package de.localchat.discovery.internal.strategy.mdns

import de.localchat.discovery.api.ServiceEventInfo
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener

class MDnsDiscoveryListener : ServiceListener {
    private val discoveryFlow = MutableSharedFlow<ServiceEventInfo>()
    private val disappearanceFlow = MutableSharedFlow<ServiceEventInfo>()

    override fun serviceAdded(event: ServiceEvent) {
        // Nothing
    }

    override fun serviceRemoved(event: ServiceEvent) {

    }

    override fun serviceResolved(event: ServiceEvent) {
        val serviceInfo = event.info
        val eventInfo = ServiceEventInfo(
            serviceInfo.type,
            serviceInfo.name,
            serviceInfo.hostAddresses
                .ifEmpty { throw IllegalStateException("MDns discovery didn't send host address") }
                .first(),
            serviceInfo.port
        )
        discoveryFlow.tryEmit(eventInfo)
    }
}