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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirmButtonOnClick(View view) {
        String email = findViewById(R.id.emailField).toString();
        String password = findViewById(R.id.passwordField).toString();

        if (email == null || password.equals(null)) {
            WarningDialogFragment warningDialogFragment = new WarningDialogFragment();
            warningDialogFragment.show(getSupportFragmentManager(), "custom");
        } else  {

        }
    }
}