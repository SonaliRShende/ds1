import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket("127.0.0.1", 3300);
            System.out.println("Connected");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(System.in));

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            String line = "";

            while (!line.equals("Over")) {
                // Send message
                System.out.print("You: ");
                line = input.readLine();
                out.writeUTF(line);

                if (line.equals("Over"))
                    break;

                // Receive reply
                String reply = in.readUTF();
                System.out.println("Server: " + reply);
            }

            socket.close();
            input.close();
            out.close();
            in.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}