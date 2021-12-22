package ua.nure.server.commands;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.domain.entity.Client;
import ua.nure.server.ServerConnection;
import ua.nure.server.application.Server;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.database.repository.ClientRepository;
import ua.nure.server.exception.RepositoryException;

public class ChangePasswordServerCommand extends Command {
    private String login;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordServerCommand() { }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public void execute() {
        try {
            ClientRepository clientRepository = ServerConnection.getClientRepository();
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();
            Client client = clientRepository.getByLogin(login);
            if (client != null) {
                client.setPassword(newPassword);
                clientRepository.update(client);
                dataOutputStream.writeBytes(Command.POSITIVE_ANSWER);
            } else {
                dataOutputStream.writeBytes(Command.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
