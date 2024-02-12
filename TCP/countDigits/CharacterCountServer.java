import java.io.*;
import java.net.*;

public class CharacterCountServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Server socket running on port 12345
            System.out.println("Server listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept client connection
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputString = in.readLine(); // Read string from client
                System.out.println("Received string from client: " + inputString);

                int alphabetCount = 0;
                int digitCount = 0;
                int specialCharCount = 0;

                // Count characters
                for (char c : inputString.toCharArray()) {
                    if (Character.isLetter(c)) {
                        alphabetCount++;
                    } else if (Character.isDigit(c)) {
                        digitCount++;
                    } else {
                        specialCharCount++;
                    }
                }

                // Send counts back to client
                out.println(alphabetCount);
                out.println(digitCount);
                out.println(specialCharCount);

                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
