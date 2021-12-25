package ua.nure.myapplication.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import json.JsonHelper;
import ua.nure.domain.entity.Client;
import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class GetUserClientCommand extends ClientCommand {
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private Client client;

    public GetUserClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public Client getClient(){
        return client;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        try {
            dataOutputStream.writeBytes(CommandsList.GET_USER_COMMAND +"\n" + MainActivity.getLogin() + "\n");
            answer = bufferedReader.readLine();
            if (answer.equals(ClientCommand.POSITIVE_ANSWER)){
                client = JsonHelper.parseJsonIntoClient(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
