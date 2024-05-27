package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private String name;
    private Socket socket;
    private DataOutputStream out;
    private int port = 4900;
    private DataInputStream in;
    Scanner scanner = new Scanner(System.in);


    public ClientHandler(Socket socket) throws IOException {
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }


    @Override
    public void run() {
        System.out.println("Enter your name: ");
        this.name = scanner.nextLine();

        System.out.println("welcome " + this.name + "\nEnter your message:");
        try {
            while (true) {
                String clientInput = new BufferedReader(new InputStreamReader(System.in)).readLine();
//                String clientInput = scanner.nextLine();
                out.writeUTF("\033[35m" + name + ":\033[0m " + clientInput);

            }
        } catch (IOException e) {
            e.printStackTrace();


        }
    }


}

