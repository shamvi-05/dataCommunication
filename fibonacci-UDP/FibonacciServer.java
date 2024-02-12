import java.net.*;

public class FibonacciServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(8080)) {
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            while (true) {
                serverSocket.receive(receivePacket);
                int fifthFibonacci = fibonacci(5);
                byte[] sendData = String.valueOf(fifthFibonacci).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int fibonacci(int n) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        else {
            int a = 0, b = 1;
            for (int i = 2; i <= n; i++) {
                int temp = b;
                b = a + b;
                a = temp;
            }
            return b;
        }
    }
}
