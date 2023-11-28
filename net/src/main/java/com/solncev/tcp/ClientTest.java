package com.solncev.tcp;

public class ClientTest {

    public static void main(String[] args) {
        GreetingClient client = new GreetingClient();
        client.start("127.0.0.1", 5555);

        System.out.println(client.send("Hello"));
        System.out.println(client.send("hellO"));
        System.out.println(client.send("1234"));
        System.out.println(client.send("bye"));
        // ?
        System.out.println(client.send("hello"));

        client.stop();
    }
}
