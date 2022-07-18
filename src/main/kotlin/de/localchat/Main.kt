package de.localchat

import de.localchat.di.modules.ApplicationModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.logger.SLF4JLogger

fun main() {
    val koinApplication = startKoin {
        logger(SLF4JLogger())
        modules(ApplicationModule().module)
        createEagerInstances()
    }
}