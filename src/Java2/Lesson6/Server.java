package Java2.Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements  Runnable{
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    ServerSocket serverSocket;
    Scanner scanner;

    public Server() {

        try {
            serverSocket = new ServerSocket(3322);
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            Socket clientSocket = serverSocket.accept();
            dataOutputStream =new DataOutputStream(clientSocket.getOutputStream());
            dataInputStream = new DataInputStream(clientSocket.getInputStream());


            new Thread(()->{
                while (true) {
                    try {
                        dataOutputStream.writeUTF(scanner.nextLine());
                    } catch (IOException e) {
                        System.out.println("Client was disconnecting.");
                        System.exit(0);
                    }
                }
            }).start();

            while(true) {
                System.out.println("client: " + dataInputStream.readUTF());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
