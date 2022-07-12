package de.localchat.network.netty

import io.netty5.bootstrap.ServerBootstrap
import io.netty5.channel.Channel
import io.netty5.channel.ChannelFactory
import io.netty5.channel.ServerChannel
import io.netty5.channel.ServerChannelFactory

abstract class NettyServerBootstrap<C : ServerChannel> : NettyAbstractBootstrap<ServerBootstrap, C>() {
    init {
        bootstrap.channelFactory(newServerChannelFactory())
    }

    abstract fun newServerChannelFactory(): ServerChannelFactory<C>

    override fun newBootstrap(): ServerBootstrap {
        return ServerBootstrap()
    }
}