package de.localchat.di.modules.discovery

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("de.localchat.discovery.udp")
class UDPDiscoveryModule