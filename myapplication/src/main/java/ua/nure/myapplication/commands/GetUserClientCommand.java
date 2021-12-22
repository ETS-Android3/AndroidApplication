package ua.nure.myapplication.commands;

import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.domain.entity.Client;
import ua.nure.myapplication.MainActivity;

public class GetUserClientCommand extends ClientCommand {
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;
    private Client client;


    public GetUserClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public Client getClient(){
        return client;
    }

    @Override
    public void execute() {
        try {
            dataOutputStream.writeBytes("GET_USER" + "\n" + MainActivity.getLogin() + "\n");
            if (bufferedReader.readLine().equals(ClientCommand.POSITIVE_ANSWER)){
                Gson gson = new Gson();
                client = gson.fromJson(bufferedReader.readLine(), Client.class);
                System.out.println("CLIENT:" + client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
