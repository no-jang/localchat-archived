package de.localchat.discovery.server;

import io.netty.channel.ChannelFuture;

public class ServerMain {
    public static void main(String[] args) throws InterruptedException {
        UDPServer server = new UDPServer(1234);
        ChannelFuture future = server.start();

        future.channel().closeFuture().sync();
    }
}
