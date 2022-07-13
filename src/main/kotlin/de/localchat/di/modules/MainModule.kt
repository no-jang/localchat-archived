package de.localchat.di.modules

import de.localchat.discovery.DiscoveryBackend
import de.localchat.discovery.udp.UDPDiscoveryBackend
import de.localchat.util.lifecycle.Lifecycle
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {
    singleOf(::UDPDiscoveryBackend) {
        bind<DiscoveryBackend>()
        bind<Lifecycle>()
    }
}