package chatappserver;

import java.io.*;
import java.util.*;
import java.net.*;

public class ChatAppServer {
   private static final int PORT = 12345;
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //listens messages from the client stores it in the server then brodcasts it to all the client
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                //read messages from the client 
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                //send messages to the client
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received message: " + message);
                    //sends messages to all the connected clients except for the sender
                    broadcast(message , this);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message , ClientHandler sender) {
            for (ClientHandler client : clients) {
                if(client != sender)
                  client.sendMessage(message);
            }
        }

        private void sendMessage(String message) {
            out.println(message);
        }
    }
    
}

