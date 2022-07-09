package de.localchat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.util.NetUtil;
import io.netty.util.internal.SocketUtils;
import org.tinylog.Logger;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.netty.Connection;
import reactor.netty.resources.LoopResources;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpServer;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Test3 {
    public static final String MULTICAST_ADDRESS = "224.0.2.60";
    public static final int MULTICAST_PORT = 4445;

    public static void main(String[] args) throws SocketException, UnknownHostException {
        final Random rndm = new Random();
        final int port = MULTICAST_PORT;
        Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();

        final InetAddress multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
        final NetworkInterface multicastInterface = findMulticastEnabledIPv4Interface();
        Logger.info("Using network interface '{}' for multicast", multicastInterface);
        final Collection<Connection> servers = new ArrayList<>();

        LoopResources resources = LoopResources.create("test");

        Connection server =
                UdpServer.create()
                        .option(ChannelOption.SO_REUSEADDR, true)
                        .bindAddress(() -> new InetSocketAddress(port))
                        .runOn(resources, InternetProtocolFamily.IPv4)
                        .handle((in, out) -> {
                            Flux.<NetworkInterface>generate(s -> {
                                        // Suppressed "JdkObsolete", usage of Enumeration is deliberate
                                        if (ifaces.hasMoreElements()) {
                                            s.next(ifaces.nextElement());
                                        }
                                        else {
                                            s.complete();
                                        }
                                    }).flatMap(iface -> {
                                        if (isMulticastEnabledIPv4Interface(iface)) {
                                            return in.join(multicastGroup,
                                                    iface);
                                        }
                                        return Flux.empty();
                                    })
                                    .thenMany(in.receiveObject())
                                    .log()
                                    .subscribe(o -> {
                                        if(o instanceof DatagramPacket) {
                                            System.out.println(((DatagramPacket) o).content().toString(StandardCharsets.UTF_8));
                                        }
                                    });
                            return Flux.never();
                        })
                        .bind()
                        .block(Duration.ofSeconds(30));

        server.onDispose().block();
    }

    private static boolean isMulticastEnabledIPv4Interface(NetworkInterface iface) {
        try {
            if (!iface.supportsMulticast() || !iface.isUp()) {
                return false;
            }
        }
        catch (SocketException se) {
            return false;
        }

        // Suppressed "JdkObsolete", usage of Enumeration is deliberate
        for (Enumeration<InetAddress> i = iface.getInetAddresses(); i.hasMoreElements();) {
            InetAddress address = i.nextElement();
            if (address.getClass() == Inet4Address.class) {
                return true;
            }
        }

        return false;
    }

    private static NetworkInterface findMulticastEnabledIPv4Interface() throws SocketException {
        if (isMulticastEnabledIPv4Interface(NetUtil.LOOPBACK_IF)) {
            return NetUtil.LOOPBACK_IF;
        }

        // Suppressed "JdkObsolete", usage of Enumeration is deliberate
        for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
            NetworkInterface iface = ifaces.nextElement();
            if (isMulticastEnabledIPv4Interface(iface)) {
                return iface;
            }
        }

        throw new UnsupportedOperationException(
                "This test requires a multicast enabled IPv4 network interface, but " + "none" + " " + "were found");
    }
}
