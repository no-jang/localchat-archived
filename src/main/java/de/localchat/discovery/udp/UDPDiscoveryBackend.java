package de.localchat.discovery.udp;

import de.localchat.discovery.DiscoveryBackend;
import de.localchat.discovery.DiscoveryListener;
import de.localchat.discovery.DiscoveryRequest;
import de.localchat.discovery.common.DefaultDiscoveryRequest;
import de.localchat.util.NumberUtil;
import org.tinylog.Logger;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.nio.charset.StandardCharsets;

public class UDPDiscoveryBackend implements DiscoveryBackend, AutoCloseable {
    private static final String MULTICAST_GROUP = "239.255.0.2";
    private static final int MULTICAST_PORT = 18942;
    private static final int MAGIC_NUMBER = 446574;
    private static final int MAGIC_NUMBER_DIGITS = NumberUtil.digits(MAGIC_NUMBER);

    private final DiscoveryListener discoveryListener;

    private boolean isRunning;
    private Thread workerThread;
    private DatagramChannel channel;

    public UDPDiscoveryBackend(DiscoveryListener discoveryListener) {
        this.discoveryListener = discoveryListener;
    }

    @Override
    public void start() throws IOException {
        if (isRunning) {
            return;
        }

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
        InetAddress broadcastGroup = InetAddress.getByName(MULTICAST_GROUP);

        channel = DatagramChannel.open(StandardProtocolFamily.INET)
                .setOption(StandardSocketOptions.SO_REUSEADDR, true)
                .setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface);

        channel.bind(new InetSocketAddress(MULTICAST_PORT));

        MembershipKey membershipKey = channel.join(broadcastGroup, networkInterface);

        workerThread = new Thread(() -> {
            ByteBuffer buffer = ByteBuffer.allocate(1500);

            while (isRunning) {
                try {
                    if(!membershipKey.isValid()) {
                        break;
                    }

                    buffer.clear();
                    InetSocketAddress senderAddress = (InetSocketAddress) channel.receive(buffer);
                    buffer.flip();

                    String content = new String(buffer.array(), StandardCharsets.US_ASCII);

                    String magicNumber = content.substring(0, MAGIC_NUMBER_DIGITS);
                    if(!magicNumber.equals(String.valueOf(MAGIC_NUMBER))) {
                        break;
                    }

                    String host = senderAddress.getAddress().getHostAddress();
                    int webPort = Integer.parseInt(content.substring(MAGIC_NUMBER_DIGITS + 1));

                    Logger.debug("Found udp discovery from " + host + " web port: " + webPort);

                    DiscoveryRequest request = new DefaultDiscoveryRequest(host, webPort);
                    discoveryListener.receivedRequest(request);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read udp discovery message", e);
                }
            }
        }, "udp discovery thread");
    }

    @Override
    public void stop() throws IOException {
        if (!isRunning) {
            return;
        }
        
        isRunning = false;

        try {
            workerThread.join(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("UDP discovery backend worker thread interrupted");
        }

        close();
    }

    @Override
    public void close() throws IOException {
        if(channel != null) {
            channel.close();
        }
    }

    @Override
    public void broadcastRequest(DiscoveryRequest request) throws IOException {
        if(!isRunning) {
            return;
        }

        String content = String.valueOf(MAGIC_NUMBER) + request.getWebPort();
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(StandardCharsets.US_ASCII));

        channel.send(buffer, new InetSocketAddress(MULTICAST_GROUP, MULTICAST_PORT));
    }
}
