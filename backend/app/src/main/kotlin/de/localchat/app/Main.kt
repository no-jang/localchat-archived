package de.localchat.app

import de.localchat.network.netty.hold.NettyEnvironmentFactory

fun main() {
    val factory = NettyEnvironmentFactory()
    val environment = factory.newEnvironment()

    println(environment.getName())
}