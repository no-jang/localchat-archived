package de.localchat.network.netty.hold

import de.localchat.network.hold.AbstractSocketPool
import de.localchat.network.hold.ClientSocket
import de.localchat.network.hold.DatagramSocket
import de.localchat.network.hold.ServerSocket
import io.netty5.channel.MultithreadEventLoopGroup
import org.slf4j.LoggerFactory

class NettySocketPool(
    environment: NettyEnvironment = NettyEnvironmentFactory.newEnvironment(),
) : AbstractSocketPool<NettyEnvironment>(
    environment, LoggerFactory.getLogger(NettySocketPool::class.java)
) {
    private val eventLoopGroup = MultithreadEventLoopGroup(environment.newHandlerFactory())

    override fun connect(name: String, remoteAddress: String, port: Int): ClientSocket {
        TODO("Not yet implemented")
    }

    override fun bind(name: String, port: Int): ServerSocket {
        TODO("Not yet implemented")
    }

    override fun bindDatagram(name: String, port: Int): DatagramSocket {
        TODO("Not yet implemented")
    }

    override fun doClose() {
        super.doClose()

        eventLoopGroup.shutdownGracefully()

        fireOnClose()
    }
}