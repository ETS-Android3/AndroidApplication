package ua.nure.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Server {
    private static final SortedMap<Object, Object> connections = Collections.synchronizedSortedMap(new TreeMap<>());
    public static final String IP = "127.0.0.1";
    public static final Integer PORT = 7194;
    private static final Integer TIMEOUT = 500;
    private static final String CLOSE_COMMAND = "CLOSE" ;
    private volatile Boolean stop = false;
    private Integer connectionID = 0;

    private Server() {}

    private static Server getInstance(){ return AppServerHolder.INSTANCE; }

    private static class AppServerHolder{ private static final Server INSTANCE = new Server(); }

    //public static Map<Integer, Connection> getConnections() { return connections; }


    private void start() throws IOException {
        startFinishCommand();

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("[SERVER]:SERVER STARTED ON PORT:" + PORT);

        while (!stop){
            Socket socket;
            serverSocket.setSoTimeout(TIMEOUT);
            try {
                socket = serverSocket.accept();
                Connection serverConnection = new Connection(connectionID, socket);
                serverConnection.start();
                connections.put(connectionID, serverConnection);
                System.out.println("[SERVER]:USER CONNECTED.CONNECTED USERS:" + connections.size());
                connectionID++;
            }catch (Exception exception){ }

        }
        serverSocket.close();
        System.out.println("[SERVER]:SERVER CLOSED");
    }




    private void startFinishCommand() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String command;
                    try {
                        command = bufferedReader.readLine();
                        if (command.toUpperCase(Locale.ROOT).equals(CLOSE_COMMAND)) {
                            stop = true;
                            bufferedReader.close();
                            break;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        Server server = Server.getInstance();
        server.start();
    }
}