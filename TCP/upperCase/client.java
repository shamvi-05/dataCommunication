import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Create a socket and connect to the server running on localhost and port 12345
            Socket socket = new Socket("localhost", 12345); 

            // Print a message indicating successful connection
            System.out.println("Connected to server..."); 

            // Create buffered readers to read user input and server responses
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); // For reading user input
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // For reading server response
            
            // PrintWriter for sending data to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Prompt the user to enter text to send to the server
            System.out.print("Enter text to send to server: "); 
            String textToSend = userInput.readLine(); // Read text from user

            // Send the text to the server
            out.println(textToSend); 

            // Receive text from the server
            String receivedText = in.readLine(); 

            // Print the received text from the server
            System.out.println("Received from server: " + receivedText);

            // Close the socket
            socket.close(); 
        } catch (IOException e) {
            // Print stack trace in case of an IOException
            e.printStackTrace(); 
        }
    }
}
