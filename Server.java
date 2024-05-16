import java.io.*;
import java.net.*;

public class Server 
{
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) 
    {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) 
        {
            System.out.println("Server is running and listening on port " + SERVER_PORT);

            // Accept client connections and handle them in separate threads
            while (true) 
            {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Start a new thread to handle client communication
                Thread clientHandler = new Thread(() -> {
                    try (
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader serverReader = new BufferedReader(new InputStreamReader(System.in))
                        ) 
                    {
                        // Create a separate thread to read messages from the server
                        Thread serverListener = new Thread(() -> 
                        {
                            String serverResponse;
                            try 
                            {
                                while ((serverResponse = serverReader.readLine()) != null) 
                                {
                                    writer.println(serverResponse);
                                }
                            } 
                            catch (IOException e) 
                            {
                                System.err.println("Error: Server connection closed.");
                            }
                        });
                        serverListener.start();

                        // Read client input and send responses
                        String clientMessage;
                        while ((clientMessage = reader.readLine()) != null) 
                        {
                            System.out.println("Client: " + clientMessage);
                        }

                        // Close resources
                        clientSocket.close();
                        System.out.println("Client disconnected: " + clientSocket);
                    } 
                    catch (IOException e) 
                    {
                        e.printStackTrace();
                    }
                });
                clientHandler.start();
            }
        } catch (IOException e) 
        {
            System.err.println("Error: Unable to start the server.");
            e.printStackTrace();
        }
    }
}
