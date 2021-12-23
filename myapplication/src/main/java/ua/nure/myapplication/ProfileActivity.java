package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import ua.nure.domain.entity.Client;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.GetUserClientCommand;

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
            if (getUserClientCommand.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                Client client = getUserClientCommand.getClient();
                userName.setText(client.getName());
                userLogin.setText(client.getLogin());
                userPhone.setText(client.getPhoneNumber());
            }
        }).start();
    }

    public void settingsButtonOnClick(View view) {
        Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}