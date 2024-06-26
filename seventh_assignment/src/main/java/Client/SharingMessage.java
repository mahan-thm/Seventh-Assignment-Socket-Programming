package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class SharingMessage implements Runnable{
    private DataInputStream in;


    public SharingMessage(Socket socket) throws IOException {
        this.in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

        try {
            while (true){
                String comeFromServer = in.readUTF();
                System.out.println(comeFromServer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
