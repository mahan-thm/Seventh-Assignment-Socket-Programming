package Client;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Objects;
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
        System.out.println("welcome " + this.name);
        System.out.println("\n1. Enter to a group chat.\n2. Download a file from server.");
        String order = scanner.nextLine();
        if (Objects.equals(order, "1")) {
            System.out.println("\nEnter your message:");
            try {
                while (true) {
                    String clientInput = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    out.writeUTF("\033[35m" + name + ":\033[0m " + clientInput);
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else if (Objects.equals(order, "2")) {
            System.out.println("Here is the name of files you can download:");
            File directory = null;
            directory = new File("G:\\UNI\\AP\\Assignments\\Seventh-Assignment-Socket-Programming\\seventh_assignment\\src\\main\\java\\Server\\data");
            File[] files = directory.listFiles();

            for (File file : files) {
                System.out.println(file.getName());
            }

            System.out.println("Type the name of file you want to download.");
            String fileName = scanner.nextLine();

            System.out.println("Where do you want to save this file? give me a full path directory.");
            String savePath = scanner.nextLine();

            File copyFile = new File("G:\\UNI\\AP\\Assignments\\Seventh-Assignment-Socket-Programming\\seventh_assignment\\src\\main\\java\\Server\\data\\" + fileName);
            File pasteFile = new File(savePath + "/" + fileName);

            try {
                FileInputStream fileInputStream = new FileInputStream(copyFile);
                FileOutputStream fileOutputStream = new FileOutputStream(pasteFile);

                byte[] bytes = new byte[1024];
                int data;
                while ((data = fileInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes,0,data);
                }
                System.out.println("File downloaded successfully.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Invalid choice");
        }
    }


}

