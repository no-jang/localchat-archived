package de.localchat.discovery.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;

public class ServerChannelInitializer extends ChannelInitializer<DatagramChannel> {
    @Override
    protected void initChannel(DatagramChannel ch) {
        ch.pipeline()
                .addLast("echo", new EchoHandler());
    }
}
