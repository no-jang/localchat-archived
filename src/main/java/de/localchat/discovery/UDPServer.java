package de.localchat.discovery;

import reactor.netty.tcp.TcpServer;

public class UDPServer {
    static final int PORT = Integer.parseInt(System.getProperty("port", "1234"));
    static final boolean WIRETAP = System.getProperty("wiretap") != null;

    public static void main(String[] args) throws Exception {
        TcpServer server =
                TcpServer.create()
                        .port(PORT)
                        .wiretap(WIRETAP)
                        .handle((in, out) -> out.send(in.receive().retain()));

        server.bindNow()
                .onDispose()
                .block();
    }
}
