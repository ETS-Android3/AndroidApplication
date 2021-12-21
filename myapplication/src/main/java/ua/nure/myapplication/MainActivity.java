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

public class MainActivity extends AppCompatActivity {
    private static Boolean state = false;
    private Socket socket = null;
    private static DataOutputStream dataOutputStream;
    private static BufferedReader bufferedReader;

    public static void setState(Boolean sstate) {
        state = sstate;
    }
    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!state) {
            new Thread(() -> {
                try {
                    socket = new Socket(Server.IP, Server.PORT);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();
        }
        else{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }
    }

    /*public void confirmButtonOnClick(View view) {
        EditText email = findViewById(R.id.emailField);
        EditText password = findViewById(R.id.passwordField);

        if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
            WarningDialog warningDialogFragment = new WarningDialog();
            warningDialogFragment.show(getSupportFragmentManager(), "CUSTOM");
            // fix " "
        } else  {
            new Thread(() -> {
                try {
                    dataOutputStream.writeBytes("LOGIN\n" +
                            email.getText().toString() + "\n" +
                            password.getText().toString() + "\n"); // fix Login

                    if (bufferedReader.readLine().equals(ClientCommand.NEGATIVE_ANSWER)) {
                        WarningDialogNoExistingUser warningDialogNoExistingUser = new WarningDialogNoExistingUser();
                        warningDialogNoExistingUser.show(getSupportFragmentManager(), "CUSTOM");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }*/


}