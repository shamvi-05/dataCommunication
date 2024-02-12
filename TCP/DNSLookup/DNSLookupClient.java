import java.io.*;
import java.net.*;

public class DNSLookupClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the URL to lookup (e.g., example.com):");
            String url = reader.readLine();

            writer.println(url);

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = serverReader.readLine();
            System.out.println("Server Response: " + response);

            writer.close();
            serverReader.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
