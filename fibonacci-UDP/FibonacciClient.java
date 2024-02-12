import java.net.*;

public class FibonacciClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            byte[] sendData = "Get fifth Fibonacci number".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 8080);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            System.out.println("Fifth Fibonacci number: " + new String(receivePacket.getData(), 0, receivePacket.getLength()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
