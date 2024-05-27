package Server;


import Client.ClientHandler;
import Client.SharingMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int port = 4900;
    public static ExecutorService threadPool = Executors.newFixedThreadPool(8);
    public static ArrayList<Socket> socketArrayList = new ArrayList<Socket>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("\033[34mServer created :) waiting for client connection...\033[0m");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("\033[32mA new client connected :)\033[0m");

                Server.socketArrayList.add(socket);
                new Thread(new ServerHandler(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
