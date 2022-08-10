package de.localchat.app

import de.localchat.network.netty.environment.NettyEnvironmentFactory

fun main() {
    val factory = NettyEnvironmentFactory()
    val environment = factory.newEnvironment()

    println(environment.getName())
}