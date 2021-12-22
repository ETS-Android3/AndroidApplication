package ua.nure.server.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.domain.entity.Client;
import ua.nure.server.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class GetUserServerCommand extends Command {
    private String login;

    public GetUserServerCommand() { }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void execute() {
        try {
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();
            Client client = ServerConnection.getClientRepository().getByLogin(login);
            if (client != null) {
                Gson gson = new Gson();
                String json = gson.toJson(client);
                System.out.println("GSON:" + json);
                dataOutputStream.writeBytes(Command.POSITIVE_ANSWER + json + "\n");
            } else {
                dataOutputStream.writeBytes(Command.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
