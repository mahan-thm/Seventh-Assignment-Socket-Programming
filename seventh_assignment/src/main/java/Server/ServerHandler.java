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
            while (true) {
                String comeFromClients = in.readUTF();
                System.out.println(comeFromClients);

                //send to clients
                for (Socket key : Server.socketArrayList) {
                    System.out.println("howManyTime");
                    if (key != socket) {
                    DataOutputStream outPrime = new DataOutputStream(key.getOutputStream());
                    outPrime.writeUTF(comeFromClients);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
