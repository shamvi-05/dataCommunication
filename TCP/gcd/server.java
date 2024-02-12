import java.io.*;
import java.net.*;

public class GCDServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Server socket running on port 12345
            System.out.println("Server listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read two numbers from the client
                int number1 = Integer.parseInt(in.readLine());
                int number2 = Integer.parseInt(in.readLine());
                System.out.println("Received numbers from client: " + number1 + ", " + number2);

                // Calculate the GCD
                int gcd = calculateGCD(number1, number2);
                out.println(gcd); // Send the GCD back to the client

                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
