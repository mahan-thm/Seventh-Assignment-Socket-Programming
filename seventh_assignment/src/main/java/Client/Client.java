package Client;

import Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WebSocket client implementation.
 */
public class Client {
    private static int port = 4900;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", port); //localhost = 127.0.0.1
        System.out.println("\033[32mConnected to server :)\033[0m");


        ClientHandler clientHandler = new ClientHandler(socket);
        Server.threadPool.execute(clientHandler);


    }
}
