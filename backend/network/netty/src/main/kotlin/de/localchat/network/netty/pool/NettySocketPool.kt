package de.localchat.network.netty.pool

import de.localchat.network.netty.environment.NettyEnvironment
import de.localchat.network.netty.environment.NettyEnvironmentFactory
import de.localchat.network.pool.AbstractSocketPool
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket
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