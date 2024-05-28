package Client;

import Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private String name;
    private final Socket socket;
    private final DataOutputStream out;
    Scanner scanner = new Scanner(System.in);


    public ClientHandler(Socket socket) throws IOException {
        this.out = new DataOutputStream(socket.getOutputStream());
        this.socket = socket;
    }


    @Override
    public void run() {
        System.out.println("\033[34mEnter your name:\033[0m");
        this.name = scanner.nextLine();
        System.out.println("\033[34mwelcome " + this.name + "\033[0m");
        System.out.println("\033[34m1. Join a group chat.\n2. Download a file from server.\n3. Exit.\033[0m");
        while (true) {
            String order = scanner.nextLine();
            if (Objects.equals(order, "1")) {
                try {
                    out.writeUTF("add socket");
                    groupChat();
                    out.writeUTF("remove socket");
                    System.out.println("\033[34m1. Join the group chat.\n2. Download a file from server.\n3. Exit.\033[0m");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (Objects.equals(order, "2")) {
                download();
                System.out.println("\033[34m1. Join the group chat.\n2. Download a file from server.\n3. Exit.\033[0m");
            } else if (Objects.equals(order, "3")) {
                break;
            } else {
                System.out.println("\033[34mInvalid choice\033[m");
            }
        }
    }

    public void groupChat() {
        System.out.println("\033[34mEnter your message:\nNOTE: Enter 0 to left the chat.\033[0m");
        try {
            while (true) {
                String clientInput = new BufferedReader(new InputStreamReader(System.in)).readLine();
                if (Objects.equals(clientInput, "0")) {
                    break;
                }
                out.writeUTF("\033[35m" + name + ":\033[0m " + clientInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void download() {
        System.out.println("\033[34mHere is the name of files you can download:\n\033[0m");
        File directory = new File("G:\\UNI\\AP\\Assignments\\Seventh-Assignment-Socket-Programming\\seventh_assignment\\src\\main\\java\\Server\\data");
        File[] files = directory.listFiles();

        for (File file : files) {
            System.out.println("\033[37m" + file.getName() + "\033[0m");
        }

        System.out.println("\033[34mType the name of file you want to download.\033[0m");
        String fileName = scanner.nextLine();

        System.out.println("\033[34mWhere do you want to save this file? give me a full path directory.\033[0m");
        String savePath = scanner.nextLine();

        File copyFile = new File("G:\\UNI\\AP\\Assignments\\Seventh-Assignment-Socket-Programming\\seventh_assignment\\src\\main\\java\\Server\\data\\" + fileName);
        File pasteFile = new File(savePath + "/" + fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(copyFile);
            FileOutputStream fileOutputStream = new FileOutputStream(pasteFile);

            byte[] bytes = new byte[1024];
            int data;
            while ((data = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, data);
            }
            System.out.println("\033[32mFile downloaded successfully.\033[0m");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


