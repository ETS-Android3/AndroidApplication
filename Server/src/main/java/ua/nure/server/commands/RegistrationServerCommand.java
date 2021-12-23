package ua.nure.server.commands;

import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.domain.entity.Client;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class RegistrationServerCommand extends ServerCommand {
    private String password;
    private String login;
    private String name;
    private String phone;

    public RegistrationServerCommand() {}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void execute() {
        try {
            Client client = ServerConnection.getClientRepository().getByLogin(login);
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();

            if (client != null) {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            } else {
                ServerConnection.getClientRepository().insert(name, login, password, phone);
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
