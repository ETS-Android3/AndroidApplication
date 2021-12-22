package ua.nure.myapplication.commands;

import com.google.gson.Gson;

import java.io.DataOutputStream;

import ua.nure.domain.entity.Client;
import ua.nure.server.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class ClientLoginCommand extends ClientCommand {
    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void execute() {

    }
}
