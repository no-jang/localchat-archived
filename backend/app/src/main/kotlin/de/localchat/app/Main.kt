package de.localchat.app

import de.localchat.network.netty.environment.DefaultNettyEnvironmentFactory

fun main() {
    val factory = DefaultNettyEnvironmentFactory()
    val environment = factory.newEnvironment()

    println(environment.getName())
}