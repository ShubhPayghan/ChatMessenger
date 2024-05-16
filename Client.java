import java.io.*;
import java.net.*;

public class Client 
{
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) 
    {
        try 
        {
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            startListening();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) 
    {
        out.println(message);
    }

    private void startListening() 
    {
        new Thread(() -> {
            String response;
            try 
            {
                while ((response = in.readLine()) != null) 
                {
                    System.out.println(response);
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopConnection() 
    {
        try 
        {
            in.close();
            out.close();
            socket.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        String serverIp = "127.0.0.1"; // Change this to the server's IP address
        int port = 12345; // Change this to the server's port
        Client client = new Client();
        client.startConnection(serverIp, port);

        // Start reading input from console and sending messages to server
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) 
        {
            try 
            {
                String message = reader.readLine();
                client.sendMessage(message);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
