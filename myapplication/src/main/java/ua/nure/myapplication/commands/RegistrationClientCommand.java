package ua.nure.myapplication.commands;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import json.JsonHelper;
import ua.nure.domain.entity.Client;
import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class RegistrationClientCommand extends ClientCommand{
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private Client client;

    public RegistrationClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        try {
            dataOutputStream.writeBytes(CommandsList.REGISTRATION_COMMAND + "\n" + JsonHelper.covertClientToJson(client) + "\n");
            answer = bufferedReader.readLine();
            if (answer.equals(ClientCommand.POSITIVE_ANSWER)) {
                client = JsonHelper.parseJsonIntoClient(bufferedReader.readLine());
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
