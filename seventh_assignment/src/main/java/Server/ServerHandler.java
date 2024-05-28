package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;
    private DataInputStream in;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String comeFromClients = in.readUTF();
                if (comeFromClients.equals("add socket")) {
                    Server.groupChatClients.add(socket);
                } else if (comeFromClients.equals("remove socket")) {
                    Server.groupChatClients.remove(socket);
                } else {
                    System.out.println(comeFromClients);
                    System.out.println(Server.groupChatClients.size());
                    //send to clients except himself
                    for (Socket key : Server.groupChatClients) {
                        if (key != socket) {
                            DataOutputStream outPrime = new DataOutputStream(key.getOutputStream());
                            outPrime.writeUTF(comeFromClients);
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
