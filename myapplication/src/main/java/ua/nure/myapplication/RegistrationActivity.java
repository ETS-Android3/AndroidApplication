package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.fragments.WarningDialog;
import ua.nure.myapplication.fragments.WarningDialogAlreadyExistingUser;
import ua.nure.myapplication.fragments.WarningDialogNoExistingUser;
import ua.nure.server.commands.Command;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void confirmRegistrationButtonOnClick(View view) {
        EditText editName = findViewById(R.id.nameField);
        EditText editEmail = findViewById(R.id.emailField);
        EditText editPassword = findViewById(R.id.passwordField);
        EditText editPhone = findViewById(R.id.phoneField);

        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String phone = editPhone.getText().toString();

        if (email.equals("") || password.equals("") || name.equals("") || phone.equals("")) {
            WarningDialog warningDialogFragment = new WarningDialog();
            warningDialogFragment.show(getSupportFragmentManager(), "CUSTOM");
        } else {
            new Thread(() -> {
                try {
                    DataOutputStream dataOutputStream = MainActivity.getDataOutputStream();
                    BufferedReader bufferedReader = MainActivity.getBufferedReader();
                    dataOutputStream.writeBytes("REGISTRATION\n" +
                            email + "\n" +
                            password + "\n" +
                            name + "\n" +
                            phone + "\n"); // fix Login
                    if (bufferedReader.readLine().equals(ClientCommand.NEGATIVE_ANSWER)) {
                        WarningDialogAlreadyExistingUser warningDialogAlreadyExistingUser = new WarningDialogAlreadyExistingUser();
                        warningDialogAlreadyExistingUser.show(getSupportFragmentManager(), "CUSTOM");
                        editEmail.setText("");
                        editName.setText("");
                        editPassword.setText("");
                        editPhone.setText("");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}