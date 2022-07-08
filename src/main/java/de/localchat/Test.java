package de.localchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.resources.LoopResources;
import reactor.netty.udp.UdpServer;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;

public class Test {
    private static class MulticastHandler extends  SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            System.out.println("receive");
        }
    }

    public static void main(String[] args) {
        InetSocketAddress groupAddress = new InetSocketAddress("239.255.27.1", 1234);

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
                    .channelFactory(() -> new NioDatagramChannel(InternetProtocolFamily.IPv4))
                    .localAddress(localAddress, groupAddress.getPort())
                    .option(ChannelOption.IP_MULTICAST_IF, ni)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new MulticastHandler());
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel)b.bind(groupAddress.getPort()).sync().channel();
            ch.joinGroup(groupAddress, ni).sync();
            ch.closeFuture().await();
        } catch (InterruptedException | SocketException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
