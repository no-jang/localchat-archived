package de.localchat.discovery.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class UDPServer {
    private final int port;
    private Channel channel;
    private EventLoopGroup workerGroup;

    public UDPServer(int port) {
        this.port = port;
    }

    public ChannelFuture start() {
        workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ServerChannelInitializer());

        ChannelFuture future = bootstrap.bind(port).syncUninterruptibly();
        channel = future.channel();

        return future;
    }

    public void stop() {
        channel.close();
        workerGroup.shutdownGracefully();
    }
}
