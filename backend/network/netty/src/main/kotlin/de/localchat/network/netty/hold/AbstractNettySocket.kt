package de.localchat.network.netty.hold

import de.localchat.network.hold.AbstractSocket
import io.netty5.bootstrap.AbstractBootstrap
import io.netty5.channel.Channel
import io.netty5.channel.ChannelOption
import io.netty5.channel.EventLoopGroup
import org.slf4j.Logger

class AbstractNettySocket<C : Channel, B : AbstractBootstrap<*, *, *>>(
    name: String,
    port: Int,
    environment: NettyEnvironment,
    eventLoopGroup: EventLoopGroup,
    bootstrap: B,
    logger: Logger
) : AbstractSocket<NettyEnvironment>(
    name,
    port,
    environment,
    logger
) {
    init {
        bootstrap
            .option(ChannelOption.SO_REUSEADDR, true)
    }
}