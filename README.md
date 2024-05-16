# Simple Java Server-Client Chat Application

This project consists of two Java programs: a server and a client. The server listens for incoming client connections and handles communication between the server and connected clients. 
Each client can send messages to the server, which will then be broadcasted to all connected clients.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher

### Files

- `Server.java`: The server-side application that accepts client connections and handles communication.
- `Client.java`: The client-side application that connects to the server and allows the user to send and receive messages.

## How to Run

### Running the Server

1. Open a terminal or command prompt.
2. Navigate to the directory containing `Server.java`.
3. Compile the server program:

    ```sh
    javac Server.java
    ```

4. Run the server:

    ```sh
    java Server
    ```

   You should see a message indicating that the server is running and listening on port `12345`.

### Running the Client

1. Open a terminal or command prompt.
2. Navigate to the directory containing `Client.java`.
3. Compile the client program:

    ```sh
    javac Client.java
    ```

4. Run the client:

    ```sh
    java Client
    ```

   The client will attempt to connect to the server at `127.0.0.1` on port `12345`.

5. To connect to a different server IP address or port, modify the `serverIp` and `port` variables in the `main` method of `Client.java` before compiling and running the client.

### Interaction

- When the client is connected, you can type messages in the client's terminal, and they will be sent to the server.
- The server will broadcast messages from one client to all other connected clients.
- Both server and client will display the messages they send and receive.
