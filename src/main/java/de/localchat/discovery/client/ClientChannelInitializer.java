package de.localchat.discovery.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.DatagramChannel;

public class ClientChannelInitializer extends ChannelInitializer<DatagramChannel> {
    @Override
    protected void initChannel(DatagramChannel ch) throws Exception {
        ch.pipeline()
                .addLast("echo", new EchoHandler());
    }
}
