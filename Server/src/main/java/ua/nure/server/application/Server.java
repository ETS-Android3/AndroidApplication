package ua.nure.server.application;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ua.nure.server.ServerConnection;
import ua.nure.server.exception.ConnectionException;

public class Server {
    private static final List<ServerConnection> SERVER_CONNECTIONS = Collections.synchronizedList(new LinkedList<>());
    public static final String IP = "10.0.2.2";
    public static final Integer PORT = 7194;
    private static final Integer TIMEOUT = 500;
    private static final String CLOSE_COMMAND = "CLOSE" ;
    private volatile Boolean stop = false;

    private Server() { }

    private static Server getInstance() {
        return AppServerHolder.INSTANCE;
    }

    private static class AppServerHolder {
        private static final Server INSTANCE = new Server();
    }

    public static List<ServerConnection> getConnections(){
        return SERVER_CONNECTIONS;
    }

    private void start() throws IOException {
        startFinishCommand();

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("[SERVER]:SERVER STARTED ON PORT:" + PORT);

        while (!stop){
            Socket socket;
            serverSocket.setSoTimeout(TIMEOUT);
            try {
                socket = serverSocket.accept();
                ServerConnection serverConnection = new ServerConnection(socket);
                serverConnection.start();
                SERVER_CONNECTIONS.add(serverConnection);
                System.out.println("[SERVER]:USER CONNECTED.CONNECTED USERS:" + SERVER_CONNECTIONS.size());
            }catch (Exception exception){ }

        }
        serverSocket.close();
        System.out.println("[SERVER]:SERVER CLOSED");
    }

    private void startFinishCommand() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String command;
                    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
                        command = bufferedReader.readLine();
                        if (command.toUpperCase(Locale.ROOT).equals(CLOSE_COMMAND)) {
                            stop = true;
                            bufferedReader.close();
                            System.out.println("[SERVER]: CURRENT COUNT OF USERS:" + SERVER_CONNECTIONS.size());
                            break;
                        }
                    } catch (IOException e) { throw new RuntimeException(); }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws ConnectionException, IOException {
        new ServerContext().start();
        Server.getInstance().start();
    }
}