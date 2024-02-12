import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPaddress {
    public static void main(String[] args) {
        try {
            // Get the InetAddress object representing the local host
            InetAddress localHost = InetAddress.getLocalHost();

            // Print the local IP address
            System.out.println("Local IP Address: " + localHost.getHostAddress());
        } catch (UnknownHostException e) {
            // Handle the case where the local IP address cannot be retrieved
            System.err.println("Unable to retrieve local IP address: " + e.getMessage());
        }
    }
}


