import java.io.*;
import java.net.*;

public class CharacterCountClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server running on localhost and port 12345
            System.out.println("Connected to server...");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Enter a string: ");
            String inputString = userInput.readLine(); // Read string from user

            out.println(inputString); // Send string to server

            int alphabetCount = Integer.parseInt(in.readLine()); // Receive alphabet count from server
            int digitCount = Integer.parseInt(in.readLine()); // Receive digit count from server
            int specialCharCount = Integer.parseInt(in.readLine()); // Receive special character count from server

            System.out.println("Alphabets count: " + alphabetCount);
            System.out.println("Digits count: " + digitCount);
            System.out.println("Special characters count: " + specialCharCount);

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
