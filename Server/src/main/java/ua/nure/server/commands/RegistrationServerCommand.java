package ua.nure.server.commands;


import java.io.DataOutputStream;
import java.io.IOException;
import json.JsonHelper;
import ua.nure.domain.entity.Client;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class RegistrationServerCommand extends ServerCommand {
    private Client mainClient;

    public RegistrationServerCommand() {}

    public void setClient(String jsonClient) {
        mainClient = JsonHelper.parseJsonIntoClient(jsonClient);
    }

    @Override
    public void execute() {
        try {
            Client client = ServerConnection.getClientRepository().getByLogin(mainClient.getLogin());
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();

            if (client != null) {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            } else {
                assert false;
                ServerConnection.getClientRepository().insert(mainClient);
                client = ServerConnection.getClientRepository().getByLogin(mainClient.getLogin());
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER + JsonHelper.covertClientToJson(client) + "\n");
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
