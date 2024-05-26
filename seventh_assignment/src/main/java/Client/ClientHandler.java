package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private String name;
    private Socket socket;
    private int port = 4900;
    Scanner scanner = new Scanner(System.in);


    public ClientHandler(Socket socket) {
        this.socket = socket;

    }


    @Override
    public void run() {
        System.out.println("Please enter your name: ");
        this.name = scanner.nextLine();

        System.out.println("welcome " + this.name + "\nEnter your message:");
        try {
            while (true) {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String clientInput = scanner.nextLine();
                out.writeUTF("\033[35m" + name + ":\033[0m " + clientInput);
//                DataInputStream in = new DataInputStream(socket.getInputStream());
//                System.out.println(in.readUTF());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
