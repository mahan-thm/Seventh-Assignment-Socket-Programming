package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SharingMessage implements Runnable{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;


    public SharingMessage(Socket socket) throws IOException {
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

        try {
            System.out.println("here1");
            while (true){
                System.out.println(in.readUTF());
                System.out.println("here2");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
