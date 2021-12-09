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

import ua.nure.server.Connection;
import ua.nure.server.exception.ConnectionException;

public class Server {
    private static final List<Connection> connections = Collections.synchronizedList(new LinkedList<Connection>());
    public static final String IP = "10.0.2.2";
    public static final Integer PORT = 7194;
    private static final Integer TIMEOUT = 500;
    private static final String CLOSE_COMMAND = "CLOSE" ;
    private volatile Boolean stop = false;

    private Server() { }

    private static Server getInstance(){ return AppServerHolder.INSTANCE; }

    private static class AppServerHolder{ private static final Server INSTANCE = new Server(); }

    private void start() throws IOException {
        startFinishCommand();

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("[SERVER]:SERVER STARTED ON PORT:" + PORT);

        while (!stop){
            Socket socket;
            serverSocket.setSoTimeout(TIMEOUT);
            try {
                socket = serverSocket.accept();
                Connection serverConnection = new Connection(socket);
                serverConnection.start();
                connections.add(serverConnection);
                System.out.println("[SERVER]:USER CONNECTED.CONNECTED USERS:" + connections.size());
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