package de.localchat.discovery.internal.strategy.mdns

import de.localchat.discovery.api.ServiceEventInfo
import de.localchat.discovery.api.ServiceInfo
import de.localchat.discovery.api.strategy.DiscoveryStrategy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener

class MDnsDiscoveryStrategy : DiscoveryStrategy {
    private val jmdns = JmDNS.create(InetAddress.getLocalHost())
    private val discoveryFlows = mutableMapOf<ServiceInfo, MutableSharedFlow<ServiceEventInfo>>()

    override fun registerService(service: ServiceInfo) {
        val serviceInfo = javax.jmdns.ServiceInfo.create(service.type, service.name, service.port, "")
        jmdns.registerService(serviceInfo)

        jmdns.addServiceListener(service.type, object : ServiceListener {
            override fun serviceAdded(event: ServiceEvent) {

            }

            override fun serviceRemoved(event: ServiceEvent) {

            }

            override fun serviceResolved(event: ServiceEvent) {

            }
        })
    }

    override fun onServiceDiscovery(service: ServiceInfo): Flow<ServiceEventInfo> {
        TODO("Not yet implemented")
    }

    override fun onServiceDisappearance(service: ServiceInfo): Flow<ServiceEventInfo> {
        TODO("Not yet implemented")
    }

    override fun unregisterService(service: ServiceInfo) {
        TODO("Not yet implemented")
    }

    override fun destroy() {
        TODO("Not yet implemented")
    }
}