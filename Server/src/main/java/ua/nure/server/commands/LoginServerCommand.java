package ua.nure.server.commands;

import java.io.DataOutputStream;
import java.io.IOException;

import json.JsonHelper;
import ua.nure.domain.entity.Client;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class LoginServerCommand extends ServerCommand {
    private String password;
    private String login;

    public LoginServerCommand () { }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void execute() {
        try {
            Client client = ServerConnection.getClientRepository().getByLogin(login);
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();
            if (client != null) {
                if (!password.equals(client.getPassword())) {
                    dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
                } else {
                    dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER  + JsonHelper.covertClientToJson(client) + "\n");
                }
            } else {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
