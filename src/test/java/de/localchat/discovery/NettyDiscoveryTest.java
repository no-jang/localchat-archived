package de.localchat.discovery;

import io.netty5.bootstrap.Bootstrap;
import io.netty5.buffer.api.Buffer;
import io.netty5.channel.*;
import io.netty5.channel.nio.NioHandler;
import io.netty5.channel.socket.DatagramChannel;
import io.netty5.channel.socket.DatagramPacket;
import io.netty5.channel.socket.InternetProtocolFamily;
import io.netty5.channel.socket.nio.NioDatagramChannel;
import io.netty5.handler.logging.LogLevel;
import io.netty5.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class NettyDiscoveryTest {
    public static final String MULTICAST_ADDRESS = "224.0.2.60";
    public static final int MULTICAST_PORT = 4445;

    public static void main(String[] args) throws SocketException, InterruptedException, ExecutionException {
        InetSocketAddress groupAddress = new InetSocketAddress(MULTICAST_ADDRESS, MULTICAST_PORT);
        EventLoopGroup group = new MultithreadEventLoopGroup(NioHandler.newFactory());
        try {
            NetworkInterface ni = NetworkInterface.getByName("enp4s0");
            //Enumeration<InetAddress> addresses = ni.getInetAddresses();
            //InetAddress localAddress = null;
            //while (addresses.hasMoreElements()) {
            //    InetAddress address = addresses.nextElement();
            //    if (address instanceof Inet4Address){
            //        localAddress = address;
            //    }
            //}

            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channelFactory(new ChannelFactory<DatagramChannel>() {
                        @Override
                        public DatagramChannel newChannel(EventLoop loop) {
                            return new NioDatagramChannel(loop, InternetProtocolFamily.IPv4);
                        }
                    })
                    ///.localAddress(localAddress, groupAddress.getPort())
                    //.option(ChannelOption.IP_MULTICAST_IF, ni)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerMulticastHandler());
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        }
                    });

            NioDatagramChannel ch = (NioDatagramChannel) b.bind(groupAddress.getPort()).asStage().sync().get();
            ch.joinGroup(groupAddress, ni).asStage().sync();

            while (true) {
                //ch.writeAndFlush(new DatagramPacket(DefaultBufferAllocators.("Hello World".getBytes(StandardCharsets.UTF_8)), groupAddress));
                //Thread.sleep(1500L);
            }

            //ch.closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static class ServerMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            Buffer content = msg.content();
            System.out.println(content.toString(StandardCharsets.UTF_8));
        }
    }
}
