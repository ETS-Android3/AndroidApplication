package ua.nure.myapplication.commands;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class LoginClientCommand extends ClientCommand {
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private String login;
    private String password;

    public LoginClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public LoginClientCommand setLogin(String login) {
        this.login = login;
        return this;
    }

    public LoginClientCommand setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        try {
            dataOutputStream.writeBytes(CommandsList.LOGIN_COMMAND + "\n" + login + "\n" + password + "\n");
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
