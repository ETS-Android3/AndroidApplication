package ua.nure.server.application;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Locale;

import ua.nure.domain.entity.CommandsList;
import ua.nure.server.commands.ChangePasswordServerCommand;
import ua.nure.server.commands.ServerCommand;
import ua.nure.server.commands.GetUserServerCommand;
import ua.nure.server.commands.LoginServerCommand;
import ua.nure.server.commands.RegistrationServerCommand;
import ua.nure.server.database.repository.ClientRepository;

public class ServerConnection extends Thread {
    private volatile Boolean isDisconnected = false;
    private static DataOutputStream dataOutputStream;
    private static ClientRepository clientRepository;
    private static BufferedReader bufferedReader;
    private final Socket socket;

    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientRepository = new ClientRepository(Server.getConnectionPool().getConnection());
    }

    public static ClientRepository getClientRepository() {
        return clientRepository;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    private String getConnectionName() {
        return getName().toUpperCase(Locale.ROOT);
    }

    @Override
    public void run() {
        String request;
        try {
            while (!isDisconnected) {
                request = bufferedReader.readLine();
                ServerCommand command;
                switch (request) {
                    case CommandsList.LOGIN_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE STARTED");
                        command = Server.getCommand(LoginServerCommand.class.getName());
                        ((LoginServerCommand)command).setLogin(bufferedReader.readLine());
                        ((LoginServerCommand)command).setPassword(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE FINISHED");
                        break;
                    case CommandsList.REGISTRATION_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE STARTED");
                        command = Server.getCommand(RegistrationServerCommand.class.getName());
                        ((RegistrationServerCommand)command).setClient(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE FINISHED");
                        break;
                    case CommandsList.GET_USER_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "GET USER CASE STARTED");
                        command = Server.getCommand(GetUserServerCommand.class.getName());
                        ((GetUserServerCommand)command).setLogin(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "GET USER CASE FINISHED");
                        break;
                    case CommandsList.CHANGE_PASSWORD_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "CHANGE PASSWORD CASE STARTED");
                        command = Server.getCommand(ChangePasswordServerCommand.class.getName());
                        ((ChangePasswordServerCommand)command).setLogin(bufferedReader.readLine());
                        ((ChangePasswordServerCommand)command).setOldPassword(bufferedReader.readLine());
                        ((ChangePasswordServerCommand)command).setNewPassword(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "CHANGE PASSWORD CASE FINISHED");
                        break;
                    case CommandsList.CLOSE_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "CLOSE CASE STARTED");
                        closeAll();
                        System.out.println("[" + getConnectionName() + "]:" + "CLOSE CASE FINISHED");
                        break;
                }
            }
        }
        catch (Exception exception) { }
    }

    private void closeAll() {
        try {
            socket.close();
            isDisconnected = true;
            bufferedReader.close();
            dataOutputStream.close();
            Server.getConnections().remove(this);
        } catch (Exception exception) {
            System.out.println("[" + getConnectionName() + "]:" + "CLOSING CURRENT THREAD ERROR");
        }
    }
}
