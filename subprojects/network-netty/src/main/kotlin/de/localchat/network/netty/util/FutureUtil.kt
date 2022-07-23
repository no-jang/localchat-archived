package de.localchat.network.netty.util

import io.netty5.channel.Channel
import io.netty5.util.concurrent.Future

fun <C : Channel> Future<C>.waitForFuture(): C =
    asStage()
        .sync()
        .get()