package com.solncev.udp;

public class Test {

    public static void main(String[] args) {

        try (GreetingClient client = new GreetingClient(); GreetingServer server = new GreetingServer()) {
            server.start();
            System.out.println(client.send("Hello"));
            System.out.println(client.send("hellO"));
            System.out.println(client.send("1234"));
            System.out.println(client.send("bye"));
            // ?
            System.out.println(client.send("hello"));

        }
    }
}
