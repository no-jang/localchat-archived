package de.localchat.di.modules

import de.localchat.di.modules.discovery.DiscoveryModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [DiscoveryModule::class])
@ComponentScan("de.localchat")
class ApplicationModule