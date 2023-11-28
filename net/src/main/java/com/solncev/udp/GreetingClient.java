package com.solncev.udp;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import static com.solncev.udp.GreetingServer.PORT;

public class GreetingClient implements Closeable {

    private DatagramSocket socket;
    private byte[] buffer;
    private InetAddress address;

    public GreetingClient(){
        try {
            this.socket = new DatagramSocket();
            this.address = InetAddress.getLocalHost();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public String send(String message) {
        try {
            buffer = message.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);

            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            return new String(packet.getData(), 0, packet.getLength());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        socket.close();
    }
}
