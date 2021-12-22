package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.fragments.WarningDialog;
import ua.nure.myapplication.fragments.WarningDialogNoExistingUser;
import ua.nure.server.application.Server;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirmButtonOnClick(View view) {
        EditText email = findViewById(R.id.emailField);
        EditText password = findViewById(R.id.passwordField);

        if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
            WarningDialog warningDialogFragment = new WarningDialog();
            warningDialogFragment.show(getSupportFragmentManager(), "CUSTOM");
            // fix " "
        } else  {
            new Thread(() -> {
                try {
                    DataOutputStream dataOutputStream = MainActivity.getDataOutputStream();
                    BufferedReader bufferedReader = MainActivity.getBufferedReader();
                    dataOutputStream.writeBytes("LOGIN\n" +
                            email.getText().toString() + "\n" +
                            password.getText().toString() + "\n"); // fix Login

                    if (bufferedReader.readLine().equals(ClientCommand.NEGATIVE_ANSWER)) {
                        WarningDialogNoExistingUser warningDialogNoExistingUser = new WarningDialogNoExistingUser();
                        warningDialogNoExistingUser.show(getSupportFragmentManager(), "CUSTOM");
                    } else {
                        MainActivity.setState(true);
                        MainActivity.setPassword(password.getText().toString());
                        MainActivity.setLogin(email.getText().toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        this.finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }

    public void registrationButtonOnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}