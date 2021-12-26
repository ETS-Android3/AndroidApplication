package ua.nure.server.application;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import json.JsonHelper;
import ua.nure.server.commands.ChangePasswordServerCommand;
import ua.nure.server.commands.GetAllCarsServerCommand;
import ua.nure.server.commands.ServerCommand;
import ua.nure.server.commands.GetUserServerCommand;
import ua.nure.server.commands.LoginServerCommand;
import ua.nure.server.commands.RegistrationServerCommand;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.exception.ConnectionException;

public class Server {
    private static final List<ServerConnection> SERVER_CONNECTIONS = Collections.synchronizedList(new LinkedList<>());
    private static final Map<String, ServerCommand> commands = new HashMap<>();
    private static ConnectionPool connectionPool;
    public static final String IP = "10.0.2.2";
    public static final Integer PORT = 7194;
    private static final Integer TIMEOUT = 500;
    private static final String CLOSE_COMMAND = "CLOSE" ;
    private volatile Boolean stop = false;

    private Server() {
        ResourceBundle resource = ResourceBundle.getBundle("DatabaseConfig");
        String dbUrl = resource.getString("db.url");
        String dbUser = resource.getString("db.user");
        String password = resource.getString("db.password");
        String poolsCount = resource.getString("db.count");
        try {
            connectionPool = new ConnectionPool(dbUrl, dbUser, password, Integer.parseInt(poolsCount));
        } catch (ConnectionException throwable) {
            throwable.printStackTrace();
        }
    }

    private static Server getInstance() {
        return AppServerHolder.INSTANCE;
    }

    private static class AppServerHolder {
        private static final Server INSTANCE = new Server();
    }

    public static ServerCommand getCommand(String command) {
        return commands.get(command);
    }

    public static List<ServerConnection> getConnections(){
        return SERVER_CONNECTIONS;
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    private void start() throws IOException {
        startFinishCommand();
        initializeCommands();
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
        new Thread(() -> {
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
        }).start();
    }

    private void initializeCommands(){
        commands.put(LoginServerCommand.class.getName(), new LoginServerCommand());
        commands.put(RegistrationServerCommand.class.getName(), new RegistrationServerCommand());
        commands.put(GetUserServerCommand.class.getName(), new GetUserServerCommand());
        commands.put(ChangePasswordServerCommand.class.getName(), new ChangePasswordServerCommand());
        commands.put(GetAllCarsServerCommand.class.getName(), new GetAllCarsServerCommand());
    }

    public static void main(String[] args) throws IOException {
        Server.getInstance().start();
    }
}