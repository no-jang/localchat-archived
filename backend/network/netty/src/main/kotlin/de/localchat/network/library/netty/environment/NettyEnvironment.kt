package de.localchat.network.library.netty.environment

import de.localchat.network.library.environment.Environment
import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

interface NettyEnvironment : Environment {
    interface Factory : Environment.Factory<NettyEnvironment>

    fun newHandlerFactory(): IoHandlerFactory

    fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel>

    fun newSocketChannelFactory(): ChannelFactory<SocketChannel>

    fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel>
}