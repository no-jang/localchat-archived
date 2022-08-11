package de.localchat.di

import de.localchat.di.api.TestApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TestApp : KoinComponent {
    private val api: TestApi by inject()

    fun run() {
        api.print()
    }
}