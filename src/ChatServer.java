import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is running and waiting for a client to connect...");
            System.out.println("Hello world");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Chat loop
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage, serverMessage;

            while (true) {
                // Read message from client
                clientMessage = input.readLine();
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client has exited the chat.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                // Send reply to client
                System.out.print("Server: ");
                serverMessage = consoleInput.readLine();
                output.println(serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chat.");
                    break;
                }
            }

            // Close resources
            socket.close();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
