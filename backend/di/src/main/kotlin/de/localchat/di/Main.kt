package de.localchat.di

import de.localchat.di.di.TestImpl2Module
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

fun main() {
    startKoin {
        modules(TestImpl2Module().module)
    }

    TestApp().run()
}