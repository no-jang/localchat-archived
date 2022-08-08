package de.localchat.app

import de.localchat.network.library.netty.environment.DefaultNettyEnvironmentFactory

fun main() {
    val factory = DefaultNettyEnvironmentFactory()
    val environment = factory.newEnvironment()

    println(environment.getName())
}