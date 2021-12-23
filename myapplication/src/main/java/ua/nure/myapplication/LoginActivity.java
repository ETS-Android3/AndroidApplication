package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ua.nure.domain.entity.CommandsList;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.LoginClientCommand;
import ua.nure.myapplication.fragments.WarningDialogFilingTheGaps;
import ua.nure.myapplication.fragments.WarningDialogNoExistingUser;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                LoginClientCommand loginClientCommand = new LoginClientCommand();
                loginClientCommand.setLogin(email);
                loginClientCommand.setPassword(password);

                if (loginClientCommand.execute().equals(ClientCommand.NEGATIVE_ANSWER)) {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogNoExistingUser.class.getName());
                } else {
                    MainActivity.setState(true);
                    MainActivity.setPassword(password);
                    MainActivity.setLogin(email);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    this.finish();
                }
            }).start();
        }
    }

    public void registrationButtonOnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}