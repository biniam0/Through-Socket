import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to the server!");

            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Chat loop
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage, serverMessage;

            while (true) {
                // Send message to server
                System.out.print("Client: ");
                clientMessage = consoleInput.readLine();
                output.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting chat.");
                    break;
                }

                // Read message from server
                serverMessage = input.readLine();
                System.out.println("Server: " + serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server has exited the chat.");
                    break;
                }
            }

            // Close resources
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
