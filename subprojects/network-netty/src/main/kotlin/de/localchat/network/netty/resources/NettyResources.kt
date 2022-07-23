package de.localchat.network.netty.resources

import de.localchat.network.NativeResources
import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

interface NettyResources : NativeResources {
    enum class NettyType(private val _name: String) : NativeResources.NativeType {
        EPOLL("epoll"),
        KQUEUE("kqueue"),
        NIO("nio");

        override fun getName(): String = _name
    }

    fun getType(): NettyType

    fun newHandlerFactory(): IoHandlerFactory
    fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel>
    fun newSocketChannelFactory(): ChannelFactory<SocketChannel>
    fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel>
}