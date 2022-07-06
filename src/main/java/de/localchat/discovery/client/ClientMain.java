package de.localchat.discovery.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;

import java.nio.charset.StandardCharsets;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        UDPClient client = new UDPClient("localhost", 1235);
        ChannelFuture future = client.start().sync();

        String message = "Hello World!";
        System.out.println("Sending message: " + message);

        ByteBuf content = Unpooled.copiedBuffer(message, StandardCharsets.UTF_8);
        client.write(content);

        future.channel().closeFuture().sync();
    }
}
