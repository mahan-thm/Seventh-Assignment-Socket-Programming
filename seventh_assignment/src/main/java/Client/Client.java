package Client;

import Server.Server;

import java.io.IOException;
import java.net.Socket;


public class Client {
    private static int port = 4900;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", port); //localhost = 127.0.0.1
        System.out.println("\033[32mConnected to server :)\033[0m");
        System.out.println(socket);
        Server.threadPool.execute(new ClientHandler(socket));

//        new Thread(new ClientHandler(socket)).start();
        new Thread(new SharingMessage(socket)).start();
    }
}
