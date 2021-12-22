package ua.nure.myapplication.commands;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.myapplication.MainActivity;

public class ChangePasswordClientCommand extends ClientCommand {
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;
    private String newPassword;
    private Boolean result;

    public ChangePasswordClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Boolean getResult() {
        return result;
    }

    @Override
    public void execute() {
        try {
            dataOutputStream.writeBytes("CHANGE_PASSWORD_COMMAND" + "\n" + MainActivity.getLogin() + "\n" + MainActivity.getPassword() + "\n" + newPassword + "\n");
            result = bufferedReader.readLine().equals(ClientCommand.POSITIVE_ANSWER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
