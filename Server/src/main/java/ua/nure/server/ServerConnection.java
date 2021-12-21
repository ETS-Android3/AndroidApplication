package ua.nure.server;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Locale;


import ua.nure.server.application.Server;
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
                switch (request) {
                    case "LOGIN":
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE STARTED");
                        LoginServerCommand loginServerCommand = (LoginServerCommand) Server.getCommand(LoginServerCommand.class.getName());
                        loginServerCommand.setLogin(bufferedReader.readLine());
                        loginServerCommand.setPassword(bufferedReader.readLine());
                        loginServerCommand.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE FINISHED");
                        break;
                    case "REGISTRATION":
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE STARTED");
                        RegistrationServerCommand registrationServerCommand = (RegistrationServerCommand) Server.getCommand(RegistrationServerCommand.class.getName());
                        registrationServerCommand.setLogin(bufferedReader.readLine());
                        registrationServerCommand.setPassword(bufferedReader.readLine());
                        registrationServerCommand.setName(bufferedReader.readLine());
                        registrationServerCommand.setPhone(bufferedReader.readLine());
                        registrationServerCommand.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE FINISHED");
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
