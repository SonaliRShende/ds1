import java.net.*;
import java.io.*;

public class Server {
	public static void main(String args[]) {
        try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(3300);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            Socket socket = server.accept();
            System.out.println("Client accepted");

            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader serverInput = new BufferedReader(
                    new InputStreamReader(System.in));

            String line = "";

            while (!line.equals("Over")) {
                // Read from client
                line = in.readUTF();
                System.out.println("Client: " + line);

                // Send reply from server
                System.out.print("Enter reply: ");
                String reply = serverInput.readLine();
                out.writeUTF(reply);

                if (reply.equals("Over"))
                    break;
            }

            socket.close();
            in.close();
            out.close();

        } catch (IOException i) {
            System.out.println(i);
        }
    }
}