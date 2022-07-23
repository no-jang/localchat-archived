package de.localchat.di.modules.discovery

import org.koin.core.annotation.Module

@Module(includes = [UDPDiscoveryModule::class])
//@ComponentScan("de.localchat")
class DiscoveryModule