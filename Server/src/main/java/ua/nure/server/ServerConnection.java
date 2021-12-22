package ua.nure.server;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Locale;


import ua.nure.server.application.Server;
import ua.nure.server.commands.ChangePasswordServerCommand;
import ua.nure.server.commands.Command;
import ua.nure.server.commands.GetUserServerCommand;
import ua.nure.server.commands.LoginServerCommand;
import ua.nure.server.commands.RegistrationServerCommand;
import ua.nure.server.database.repository.ClientRepository;

public class ServerConnection extends Thread {
    private final Socket socket;
    private volatile Boolean isDisconnected = false;
    private static DataOutputStream dataOutputStream;
    private static BufferedReader bufferedReader;
    private static ClientRepository clientRepository;

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
        String request = null;
        try {
            while (!isDisconnected) {
                request = bufferedReader.readLine();
                Command command = null;
                switch (request) {
                    case "LOGIN":
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE STARTED");
                        command = Server.getCommand(LoginServerCommand.class.getName());
                        ((LoginServerCommand)command).setLogin(bufferedReader.readLine());
                        ((LoginServerCommand)command).setPassword(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE FINISHED");
                        break;
                    case "REGISTRATION":
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE STARTED");
                        command = Server.getCommand(RegistrationServerCommand.class.getName());
                        ((RegistrationServerCommand)command).setLogin(bufferedReader.readLine());
                        ((RegistrationServerCommand)command).setPassword(bufferedReader.readLine());
                        ((RegistrationServerCommand)command).setName(bufferedReader.readLine());
                        ((RegistrationServerCommand)command).setPhone(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE FINISHED");
                        break;
                    case "GET_USER":
                        System.out.println("[" + getConnectionName() + "]:" + "GET USER CASE STARTED");
                        command = Server.getCommand(GetUserServerCommand.class.getName());
                        ((GetUserServerCommand)command).setLogin(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "GET USER CASE FINISHED");
                        break;
                    case "CHANGE_PASSWORD_COMMAND":
                        System.out.println("[" + getConnectionName() + "]:" + "CHANGE PASSWORD CASE STARTED");
                        command = Server.getCommand(ChangePasswordServerCommand.class.getName());
                        ((ChangePasswordServerCommand)command).setLogin(bufferedReader.readLine());
                        ((ChangePasswordServerCommand)command).setOldPassword(bufferedReader.readLine());
                        ((ChangePasswordServerCommand)command).setNewPassword(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "CHANGE PASSWORD CASE FINISHED");
                        break;
                    case "CLOSE":
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
