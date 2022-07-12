package de.localchat.network.netty

import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.Channel
import io.netty5.channel.ChannelFactory

abstract class NettyBootstrap<C : Channel> : NettyAbstractBootstrap<Bootstrap, C>() {
    init {
        bootstrap.channelFactory(newChannelFactory())
    }

    abstract fun newChannelFactory(): ChannelFactory<C>

    override fun newBootstrap(): Bootstrap {
        return Bootstrap()
    }
}