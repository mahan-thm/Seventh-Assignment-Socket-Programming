package Server;

import Client.SharingMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ServerHandler(Socket socket) throws IOException {
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("serverHandler 1");
            while (true) {
                System.out.println("serverHandler 2");
                String comeFromClients = in.readUTF();
                System.out.println(comeFromClients);

                System.out.println("serverHandler 3");
                out.writeUTF(comeFromClients);

                System.out.println("serverHandler 4");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
