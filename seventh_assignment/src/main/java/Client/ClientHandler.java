package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private String name;
    private DataOutputStream out;
    Scanner scanner = new Scanner(System.in);


    public ClientHandler(Socket socket) throws IOException {
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
                out.writeUTF("\033[35m" + name + ":\033[0m " + clientInput);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}

