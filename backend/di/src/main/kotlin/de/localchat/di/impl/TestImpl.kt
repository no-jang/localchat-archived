package de.localchat.di.impl

import de.localchat.di.api.TestApi
import org.koin.core.annotation.Single

@Single
class TestImpl : TestApi {
    override fun print() {
        println("Hello World")
    }
}