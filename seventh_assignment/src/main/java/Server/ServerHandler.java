package Server;

import java.io.DataInputStream;
import java.io.IOException;

public class ServerHandler implements Runnable {
    private DataInputStream in;

    public ServerHandler(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
