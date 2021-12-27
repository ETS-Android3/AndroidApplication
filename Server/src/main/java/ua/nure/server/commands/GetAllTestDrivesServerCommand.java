package ua.nure.server.commands;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.TestDrive;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.exception.RepositoryException;

public class GetAllTestDrivesServerCommand extends ServerCommand {
    private Client client;

    public GetAllTestDrivesServerCommand() { }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void execute() {
        try {
            List<TestDrive> testDrives = ServerConnection.getTestDriveRepository().getAllByClient(client);
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();

            if (testDrives.size() > 0) {
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER);
                dataOutputStream.writeBytes(JsonHelper.convertTestDrivesListToJson(testDrives) + "\n");
            } else {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
