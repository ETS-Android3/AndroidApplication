package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ua.nure.domain.entity.Client;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.RegistrationClientCommand;
import ua.nure.myapplication.fragments.WarningDialogFilingTheGaps;
import ua.nure.myapplication.fragments.WarningDialogAlreadyExistingUser;
import utility.CommandsList;

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
        String email = editEmail.getText().toString().replace(CommandsList.GAP_STRING, CommandsList.EMPTY_STRING);
        String password = editPassword.getText().toString().replace(CommandsList.GAP_STRING, CommandsList.EMPTY_STRING);
        String phone = editPhone.getText().toString().replace(CommandsList.GAP_STRING, CommandsList.EMPTY_STRING);

        if (email.equals(CommandsList.EMPTY_STRING) || password.equals(CommandsList.EMPTY_STRING)  || name.equals(CommandsList.EMPTY_STRING)  || phone.equals(CommandsList.EMPTY_STRING) ) {
            MainActivity.getWarningsHelper().showFragment(this, WarningDialogFilingTheGaps.class.getName());
        } else {
            new Thread(() -> {
                ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(RegistrationClientCommand.class.getName());
                ((RegistrationClientCommand)command).setClient(new Client(0, email, name, password, phone));
                if (command.execute().equals(ClientCommand.NEGATIVE_ANSWER)) {
                    MainActivity.getWarningsHelper().showFragment(this, WarningDialogAlreadyExistingUser.class.getName());
                    editEmail.setText(CommandsList.EMPTY_STRING);
                } else {
                    MainActivity.setClient(((RegistrationClientCommand) command).getClient());
                    System.out.println(MainActivity.getClient());
                    MainActivity.setViewableState(true);

                    startActivity(new Intent(RegistrationActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finish();
                }
            }).start();

        }
    }
}