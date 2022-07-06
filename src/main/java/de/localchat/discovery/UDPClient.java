package de.localchat.discovery;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;
import reactor.netty.udp.UdpClient;

public class UDPClient {
    static final int PORT = Integer.parseInt(System.getProperty("port", "1234"));
    static final boolean WIRETAP = System.getProperty("wiretap") != null;

    public static void main(String[] args) {
        TcpClient client =
                TcpClient.create()
                        .port(PORT)
                        .wiretap(WIRETAP);

        Connection connection =
                client.handle((in, out) -> out.send(Flux.concat(ByteBufFlux.fromString(Mono.just("echo")),
                                in.receive().retain())))
                        .connectNow();

        connection.onDispose()
                .block();
    }
}
