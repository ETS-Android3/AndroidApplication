package ua.nure.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ua.nure.domain.entity.Client;
import ua.nure.myapplication.commands.ChangePasswordClientCommand;
import ua.nure.myapplication.commands.GetUserClientCommand;
import ua.nure.myapplication.fragments.WarningDialog;
import ua.nure.myapplication.fragments.WarningDialogPasswordFailed;
import ua.nure.myapplication.fragments.WarningDialogPasswordSucceed;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void confirmButtonOnClick(View view) {
        EditText oldPasswordText = findViewById(R.id.oldPasswordText);
        EditText newPasswordText = findViewById(R.id.newPasswordText);

        String oldPassword = oldPasswordText.getText().toString().replace(" ","");
        String newPassword = newPasswordText.getText().toString().replace(" ", "");

        if (oldPassword.equals("") || newPassword.equals("")) {
            WarningDialog warningDialogFragment = new WarningDialog();
            warningDialogFragment.show(getSupportFragmentManager(), "CUSTOM");
        } else if (!MainActivity.getPassword().equals(oldPassword))  {
            WarningDialogPasswordFailed dialogPasswordSucceed = new WarningDialogPasswordFailed();
            dialogPasswordSucceed.show(getSupportFragmentManager(), "CUSTOM");
        } else {
            new Thread(() -> {
                ChangePasswordClientCommand changePasswordClientCommand = new ChangePasswordClientCommand();
                changePasswordClientCommand.setNewPassword(newPassword);
                changePasswordClientCommand.execute();
                MainActivity.setPassword(newPassword);
                WarningDialogPasswordSucceed dialogPasswordSucceed = new WarningDialogPasswordSucceed();
                dialogPasswordSucceed.show(getSupportFragmentManager(), "CUSTOM");
                this.finish();
            }).start();
        }

    }
}