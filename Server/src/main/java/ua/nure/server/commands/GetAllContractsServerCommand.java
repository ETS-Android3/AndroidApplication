package ua.nure.server.commands;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.Contract;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class GetAllContractsServerCommand extends ServerCommand {
    private Client client;

    public GetAllContractsServerCommand() { }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void execute() {
        try {
            List<Contract> contracts = ServerConnection.getContractRepository().getAllByClient(client);
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();

            if (contracts.size() > 0) {
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER);
                dataOutputStream.writeBytes(JsonHelper.convertContractsListToJson(contracts) + "\n");
            } else {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
