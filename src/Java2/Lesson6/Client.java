package Java2.Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {
    Socket clientSocket;
    DataInputStream in;
    DataOutputStream out;
    Scanner scanner;

    public Client() {

        try {
            clientSocket = new Socket("localhost", 3322);
            out = new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            try {
                Thread.sleep(10000);
                clientSocket = new Socket("localhost",3322);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (UnknownHostException unknownHostException) {
                System.out.println("Coudn't find the host");
                throw new RuntimeException("no route to host",unknownHostException);
            } catch (IOException ioException) {
                System.out.println("Connection refused");
                throw new RuntimeException("Connection refused",ioException);
            }
        }
    }


    @Override
    public void run() {
        if (clientSocket.isConnected()) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println("message from server: " + in.readUTF());
                    } catch (IOException e) {
                        System.out.println("is connection alive? " + clientSocket.isConnected());
                        throw new RuntimeException("connection lost", e);
                    }
                }
            })
                    .start();
            try {
                while (true) {
                    System.out.print("(enter message): ");
                    out.writeUTF(scanner.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
