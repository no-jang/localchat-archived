package de.localchat.di

import de.localchat.di.di.TestImpl2Module
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.logger.SLF4JLogger

fun main() {
    startKoin {
        logger(SLF4JLogger())
        modules(TestImpl2Module().module)
    }

    TestApp().run()
}