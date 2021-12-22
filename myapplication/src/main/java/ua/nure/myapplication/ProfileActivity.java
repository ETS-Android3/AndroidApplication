package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import ua.nure.domain.entity.Client;
import ua.nure.myapplication.commands.GetUserClientCommand;
import ua.nure.server.commands.GetUserServerCommand;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView userName = findViewById(R.id.nameView);
        TextView userLogin = findViewById(R.id.loginView);
        TextView userPhone = findViewById(R.id.phoneView);

        new Thread(() -> {

                GetUserClientCommand getUserClientCommand = new GetUserClientCommand();
                getUserClientCommand.execute();
                Client client = getUserClientCommand.getClient();

                userName.setText(client.getName());
                userLogin.setText(client.getLogin());
                userPhone.setText(client.getPhoneNumber());

        }).start();
    }

    public void settingsButtonOnClick(View view) {
        Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}