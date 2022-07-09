package de.localchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.Connection;
import reactor.netty.resources.LoopResources;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpServer;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class Test {
    public static final String MULTICAST_ADDRESS = "224.0.2.60";
    public static final int MULTICAST_PORT = 4445;

    public static class ServerMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            ByteBuf content = msg.content();
            System.out.println(content.toString(StandardCharsets.UTF_8));
        }
    }

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        InetSocketAddress groupAddress = new InetSocketAddress(MULTICAST_ADDRESS, MULTICAST_PORT);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            NetworkInterface ni = NetworkInterface.getByName("enp4s0");
            Enumeration<InetAddress> addresses = ni.getInetAddresses();
            InetAddress localAddress = null;
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet4Address){
                    localAddress = address;
                }
            }

            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channelFactory(new ChannelFactory<NioDatagramChannel>() {
                        @Override
                        public NioDatagramChannel newChannel() {
                            return new NioDatagramChannel(InternetProtocolFamily.IPv4);
                        }
                    })
                    ///.localAddress(localAddress, groupAddress.getPort())
                    //.option(ChannelOption.IP_MULTICAST_IF, ni)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerMulticastHandler());
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel)b.bind(groupAddress.getPort()).sync().channel();
            ch.joinGroup(groupAddress, ni).sync();

            while (true) {
                ch.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("Hello World".getBytes(StandardCharsets.UTF_8)), groupAddress));
                Thread.sleep(1500L);
            }

            //ch.closeFuture().await();
        } catch (InterruptedException | SocketException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
