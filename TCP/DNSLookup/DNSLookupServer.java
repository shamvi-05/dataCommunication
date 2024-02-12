import java.io.*;
import java.net.*;
import java.util.HashMap;

public class DNSLookupServer {
    private static HashMap<String, String> dnsTable = new HashMap<>();
    private static HashMap<String, Integer> hitCount = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a new thread for each client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String url;
                while ((url = reader.readLine()) != null) {
                    String ipAddress = dnsTable.get(url);
                    if (ipAddress != null) {
                        hitCount.put(url, hitCount.getOrDefault(url, 0) + 1);
                        writer.println("IP Address: " + ipAddress + " (Hit Count: " + hitCount.get(url) + ")");
                    } else {
                        InetAddress address = InetAddress.getByName(url);
                        ipAddress = address.getHostAddress();
                        dnsTable.put(url, ipAddress);
                        hitCount.put(url, 1);
                        writer.println("IP Address: " + ipAddress + " (Hit Count: 1)");
                    }
                }

                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
