package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.LoginClientCommand;
import ua.nure.myapplication.fragments.WarningDialogFilingTheGaps;
import ua.nure.myapplication.fragments.WarningDialogNoExistingUser;
import utility.CommandsList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MainActivity.setConnectedState(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirmButtonOnClick(View view) {
        EditText emailEdit = findViewById(R.id.emailField);
        EditText passwordEdit = findViewById(R.id.passwordField);
        String email = emailEdit.getText().toString().replace(CommandsList.GAP_STRING, CommandsList.EMPTY_STRING);
        String password = passwordEdit.getText().toString().replace(CommandsList.GAP_STRING, CommandsList.EMPTY_STRING);

        if (email.equals(CommandsList.EMPTY_STRING) || password.equals(CommandsList.EMPTY_STRING)) {
            MainActivity.getWarningsHelper().showFragment(this, WarningDialogFilingTheGaps.class.getName());
        } else  {

            new Thread(() -> {
                ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(LoginClientCommand.class.getName());

                if (((LoginClientCommand)command).setLogin(email).setPassword(password).execute().equals(ClientCommand.NEGATIVE_ANSWER)) {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogNoExistingUser.class.getName());
                } else {
                    MainActivity.setViewableState(true);
                    MainActivity.setClient(((LoginClientCommand)command).getClient());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            }).start();

        }
    }

    public void registrationButtonOnClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}