package ua.nure.myapplication.commands;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.domain.entity.CommandsList;
import ua.nure.myapplication.MainActivity;

public class ChangePasswordClientCommand extends ClientCommand {
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private String newPassword;

    public ChangePasswordClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public ChangePasswordClientCommand setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        try {
            dataOutputStream.writeBytes(CommandsList.CHANGE_PASSWORD_COMMAND + "\n" + MainActivity.getLogin() + "\n" + MainActivity.getPassword() + "\n" + newPassword + "\n");
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
