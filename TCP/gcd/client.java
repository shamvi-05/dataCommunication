import java.io.*;
import java.net.*;

public class GCDClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server running on localhost and port 12345
            System.out.println("Connected to server...");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read two numbers from the user
            System.out.print("Enter the first number: ");
            int number1 = Integer.parseInt(userInput.readLine());
            System.out.print("Enter the second number: ");
            int number2 = Integer.parseInt(userInput.readLine());

            // Send the numbers to the server
            out.println(number1);
            out.println(number2);

            // Receive and print the GCD from the server
            int gcd = Integer.parseInt(in.readLine());
            System.out.println("GCD received from server: " + gcd);

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
import java.io.*;
import java.net.*;

public class GCDClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server running on localhost and port 12345
            System.out.println("Connected to server...");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Read two numbers from the user
            System.out.print("Enter the first number: ");
            int number1 = Integer.parseInt(userInput.readLine());
            System.out.print("Enter the second number: ");
            int number2 = Integer.parseInt(userInput.readLine());

            // Send the numbers to the server
            out.println(number1);
            out.println(number2);

            // Receive and print the GCD from the server
            int gcd = Integer.parseInt(in.readLine());
            System.out.println("GCD received from server: " + gcd);

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
