package ua.nure.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Locale;

import ua.nure.domain.entity.Client;
import ua.nure.server.application.Server;
import ua.nure.server.database.repository.ClientRepository;

public class ServerConnection extends Thread {
    public volatile Boolean isDisconnected = false;
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private final Socket socket;

    public ServerConnection(Socket socket) throws IOException {
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
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
                        String login = bufferedReader.readLine();
                        String password = bufferedReader.readLine();
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE STARTED");
                        ClientRepository clientRepository = new ClientRepository(Server.getConnectionPool().getConnection());
                        Client client = clientRepository.getByLogin(login);
                        System.out.println(client);
                        if (!password.equals(client.getPassword())){
                            dataOutputStream.writeBytes("NO\n");
                        } else {
                            dataOutputStream.writeBytes("YES\n");
                        }

                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE FINISHED");
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
