import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class Web {

    public static void main(String[] args) throws Exception {

        // Create server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Create API endpoint
        server.createContext("/add", (exchange) -> {

            // Get query parameters
            String query = exchange.getRequestURI().getQuery();
            String[] params = query.split("&");

            int a = Integer.parseInt(params[0].split("=")[1]);
            int b = Integer.parseInt(params[1].split("=")[1]);

            int result = a + b;

            String response = "Result = " + result;

            // Send response
            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();

        System.out.println("Server running at http://localhost:8000");
    }
}