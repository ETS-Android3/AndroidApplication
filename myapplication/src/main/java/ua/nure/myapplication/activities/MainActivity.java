package ua.nure.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import ua.nure.domain.entity.Client;
import ua.nure.myapplication.R;
import ua.nure.myapplication.commands.ClientCommandService;
import ua.nure.myapplication.fragments.WarningsHelper;
import ua.nure.server.application.Server;

public class MainActivity extends AppCompatActivity {
    private static final WarningsHelper warningsHelper = WarningsHelper.getInstance();
    private static ClientCommandService clientCommandsHolder;
    private static Boolean connectedState = false;
    private static Boolean viewableState = false;
    private static DataOutputStream dataOutputStream;
    private static BufferedReader bufferedReader;
    private Socket socket = null;
    private static Client client;

    public static void setViewableState(Boolean state) {
        viewableState = state;
    }

    public static void setConnectedState(Boolean state) {
        connectedState = state;
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client someClient) {
        client = someClient;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public static ClientCommandService getClientCommandsHolder() {
        return clientCommandsHolder;
    }

    public static WarningsHelper getWarningsHelper() {
        return warningsHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!connectedState) {
            new Thread(() -> {
                try {
                    socket = new Socket(Server.IP, Server.PORT);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    clientCommandsHolder = ClientCommandService.getInstance();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        if(!viewableState) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void carsListButtonOnClick(View view) {
        startActivity(new Intent(MainActivity.this, CarsListActivity.class));

    }

    public void profileButtonOnClick(View view) {
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }
}