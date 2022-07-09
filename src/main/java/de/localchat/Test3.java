package de.localchat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import reactor.core.publisher.Flux;
import reactor.netty.Connection;
import reactor.netty.resources.LoopResources;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpServer;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class Test3 {
    public static final String MULTICAST_ADDRESS = "224.0.2.60";
    public static final int MULTICAST_PORT = 4445;

    public static class ServerMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            ByteBuf content = msg.content();
            System.out.println(content.toString(StandardCharsets.UTF_8));
        }
    }

    public static void main(String[] args) throws SocketException {
        InetSocketAddress groupAddress = new InetSocketAddress(MULTICAST_ADDRESS, MULTICAST_PORT);
        NetworkInterface ni = NetworkInterface.getByName("enp4s0");
        Enumeration<InetAddress> addresses = ni.getInetAddresses();
        InetAddress localAddress = null;
        while (addresses.hasMoreElements()) {
            InetAddress address = addresses.nextElement();
            if (address instanceof Inet4Address){
                localAddress = address;
            }
        }

        InetAddress finalLocalAddress = localAddress;
        Connection connection = UdpClient.create()
                .bindAddress(() -> new InetSocketAddress(finalLocalAddress, groupAddress.getPort()))
                .port(MULTICAST_PORT)
                .remoteAddress(() -> groupAddress)
                .runOn(LoopResources.create("udp"), InternetProtocolFamily.IPv4)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handle((in, out) -> {
                    in.join(groupAddress.getAddress(), ni).log().block();
                    in.receiveObject().log().subscribe(o -> {
                        System.out.println(o);
                        if(o instanceof DatagramPacket) {
                            ByteBuf content = ((DatagramPacket) o).content();
                            System.out.println(content.toString(StandardCharsets.UTF_8));
                        }
                    });
                    return Flux.never();
                })
                .connectNow();

        connection.onDispose().block();
    }
}
