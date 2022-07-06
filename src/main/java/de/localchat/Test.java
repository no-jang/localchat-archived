package de.localchat;

import io.netty.channel.ChannelOption;
import io.netty.channel.socket.InternetProtocolFamily;
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
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int port = 44558;
        InetSocketAddress multicastGroup = new InetSocketAddress("239.255.27.1", port);

        NetworkInterface multicastInterface = Collections.list(NetworkInterface.getNetworkInterfaces())
                        .stream().filter(networkInterface -> {
                    try {
                        return networkInterface.isUp() && networkInterface.supportsMulticast();
                    } catch (SocketException e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElseThrow();

        LoopResources resources = LoopResources.create("loop");

        UdpServer.create()
                //.option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .bindAddress(() -> multicastGroup)
                .runOn(resources, InternetProtocolFamily.IPv4)
                .handle((in, out) -> {
                    in.join(multicastGroup.getAddress(), multicastInterface);
                    in.receive().log().subscribe(byteBuf -> System.out.println("ue"));
                    return Flux.never();
                })
                .bind()
                .block();

        MulticastSocket multicast = new MulticastSocket();
        multicast.joinGroup(multicastGroup, multicastInterface);

        byte[] data = new byte[1024];
        random.nextBytes(data);

        multicast.send(new DatagramPacket(data, data.length, multicastGroup.getAddress(), port));
        multicast.close();
    }
}
