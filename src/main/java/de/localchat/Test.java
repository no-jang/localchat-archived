package de.localchat;

import io.netty.channel.ChannelOption;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.InternetProtocolFamily;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.Connection;
import reactor.netty.resources.LoopResources;
import reactor.netty.udp.UdpClient;
import reactor.netty.udp.UdpServer;

import java.net.InetSocketAddress;

public class Test {
    public static void main(String[] args) {
        UdpServer.create()
                .bindAddress(() -> new InetSocketAddress("255.255.255.255", 4567))
                .option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handle((in, out) -> {
                    in.receive()
                            .log()
                            .subscribe(byteBuf -> System.out.println("Server received"));
                    return Flux.never();
                })
                .bindNow();

        Connection clientConnection = UdpClient.create()
                //.runOn(LoopResources.create("udp"), InternetProtocolFamily.IPv4)
                //.option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handle((in, out) -> {
                    in.receive()
                            .log()
                            .subscribe(byteBuf -> System.out.println("Client received"));
                    //return out.sendString(Mono.just("Hello! "));
                    return Flux.never();
                })
                .connectNow();

        clientConnection.outbound().sendString(Mono.just("Hello!"));
    }
}
