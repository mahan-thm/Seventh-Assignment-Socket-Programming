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

//        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in)); //reads from keyboard
//        System.out.println("Enter your message");
//        String input = clientInput.readLine();
//        PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
//        socketOutput.println("[CLIENT]: " + input); // it sends this message to socket
//        BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        System.out.println(socketInput.readLine()); // it takes the massage from socket


        ClientHandler clientHandler = new ClientHandler(socket);
        Server.threadPool.execute(clientHandler);


//        DataInputStream in = new DataInputStream(socket.getInputStream());
//        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//        System.out.println("Enter your message");
//        String clientInput = scanner.nextLine();
//        out.writeUTF("[SERVER]: " + clientInput);


    }
}
