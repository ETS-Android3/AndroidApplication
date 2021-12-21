package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import ua.nure.server.application.Server;

public class MainActivity extends AppCompatActivity {
    private Socket socket = null;
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;

    private void connectToServer() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new Thread(() -> {
            try {
                socket = new Socket(Server.IP, Server.PORT);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                //bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void confirmButtonOnClick(View view) throws IOException {
        EditText email = findViewById(R.id.emailField);
        EditText password = findViewById(R.id.passwordField);

        if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
            WarningDialogFragment warningDialogFragment = new WarningDialogFragment();
            warningDialogFragment.show(getSupportFragmentManager(), "CUSTOM");
        } else  {
            dataOutputStream.writeBytes(email.getText().toString() + "\n");
            dataOutputStream.writeBytes(password.getText().toString() + "\n");
            if (bufferedReader.readLine().equals("NO")) {
                WarningDialogFragment warningDialogFragment = new WarningDialogFragment();
                warningDialogFragment.show(getSupportFragmentManager(), "CUSTOM");
            }

        }
    }
}