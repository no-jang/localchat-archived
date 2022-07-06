package de.localchat.discovery.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class UDPClient {
    private final String host;
    private final int port;

    private EventLoopGroup workerGroup;
    private Channel channel;

    public UDPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public ChannelFuture start() {
        workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ClientChannelInitializer());

        ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(host, port));
        channelFuture.syncUninterruptibly();

        channel = channelFuture.channel();

        return channelFuture;
    }

    public ChannelFuture write(Object msg) throws InterruptedException {
        return channel.writeAndFlush(msg).sync();
    }

    public void stop() {
        channel.close();
        workerGroup.shutdownGracefully();
    }
}
