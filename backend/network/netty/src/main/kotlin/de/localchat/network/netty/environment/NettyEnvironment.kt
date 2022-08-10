package de.localchat.network.netty.environment

import de.localchat.network.environment.Environment
import io.netty5.channel.ChannelFactory
import io.netty5.channel.IoHandlerFactory
import io.netty5.channel.ServerChannelFactory
import io.netty5.channel.socket.DatagramChannel
import io.netty5.channel.socket.ServerSocketChannel
import io.netty5.channel.socket.SocketChannel

interface NettyEnvironment : Environment {
    fun newHandlerFactory(): IoHandlerFactory

    fun newDatagramChannelFactory(): ChannelFactory<DatagramChannel>

    fun newSocketChannelFactory(): ChannelFactory<SocketChannel>

    fun newServerSocketChannelFactory(): ServerChannelFactory<ServerSocketChannel>
}