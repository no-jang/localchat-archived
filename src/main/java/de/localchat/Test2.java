package de.localchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class Test2 {
    public static class LanServerDetector extends Thread {
        private final InetAddress multicastAddress;
        private final MulticastSocket socket;

        public LanServerDetector() throws IOException {
            super("LanServerDetector");
            this.multicastAddress = InetAddress.getByName("224.0.2.60");
            this.socket = new MulticastSocket(4445);
            this.socket.setSoTimeout(60000);
            this.socket.joinGroup(multicastAddress);
        }

        @Override
        public void run() {
            byte[] data = new byte[1024];

            while (!this.isInterrupted()) {
                DatagramPacket packet = new DatagramPacket(data, data.length);

                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String message = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
                System.out.println(message);
            }

            try {
                socket.leaveGroup(multicastAddress);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            socket.close();;
        }
    }

    public static class LanServerPinger extends Thread {
        private InetAddress multicastAddress;
        private DatagramSocket socket;

        public LanServerPinger() throws IOException {
            this.multicastAddress = InetAddress.getByName("224.0.2.60");
            this.socket = new DatagramSocket();
        }

        @Override
        public void run() {
            byte[] data = new String("Hello World!").getBytes(StandardCharsets.UTF_8);

            while (!this.isInterrupted()) {
                try {
                    DatagramPacket packet = new DatagramPacket(data, data.length, multicastAddress, 4445);
                    socket.send(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    sleep(1500L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LanServerDetector detector = new LanServerDetector();
        //LanServerPinger pinger = new LanServerPinger();

        detector.start();
        //Thread.sleep(1000L);
        //pinger.start();
    }
}
