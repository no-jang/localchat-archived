package de.localchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.Connection;
import reactor.netty.resources.LoopResources;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class Test {
    public static final String MULTICAST_ADDRESS = "224.0.2.60";
    public static final int MULTICAST_PORT = 4445;

    public static class ServerMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            System.out.println(msg.toString());
        }
    }

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        EventLoopGroup group = new EpollEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .remoteAddress(MULTICAST_ADDRESS, MULTICAST_PORT)
                .group(group)
                .channel(EpollDatagramChannel.class)
                .handler(new ChannelInitializer<DatagramChannel>() {
                    @Override
                    protected void initChannel(DatagramChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerMulticastHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect();
        future.channel().closeFuture().sync();

        group.shutdownGracefully();
    }
}
