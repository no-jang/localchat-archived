package de.localchat.di.modules.discovery

import org.koin.core.annotation.Module

@Module(includes = [UDPDiscoveryModule::class])
class DiscoveryModule