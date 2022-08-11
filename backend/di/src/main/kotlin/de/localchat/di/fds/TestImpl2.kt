package de.localchat.di.fds

import de.localchat.di.api.TestApi
import org.koin.core.annotation.Single

@Single
class TestImpl2 : TestApi {
    override fun print() {
        println("Test")
    }
}