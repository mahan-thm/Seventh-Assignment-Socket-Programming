package Server;

import Client.SharingMessage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;
    private DataInputStream in;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.in = new DataInputStream(socket.getInputStream());
            System.out.println("there 1");
            while (true) {
                System.out.println("there 2");
                System.out.println(in.readUTF());
                System.out.println("there 3");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
