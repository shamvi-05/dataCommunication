
package chatappserver;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatAppClient{
    private static final String SERVER_IP = "127.0.0.1"; // Change to server IP if necessary
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Thread to receive messages from server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = serverInput.readLine()) != null) {
                        System.out.println("Server :  " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            // Thread to send messages to server
            Thread sendThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = userInput.readLine()) != null) {
                        out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            sendThread.start();
            
            // Wait for both threads to finish
            receiveThread.join();
            sendThread.join();
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

