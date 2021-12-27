package ua.nure.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ua.nure.myapplication.R;
import ua.nure.myapplication.adapters.TestDriveAdapter;
import ua.nure.myapplication.adapters.TestDriveListItem;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.GetAllTestDrivesClientCommand;

public class TestDriveListActivity extends AppCompatActivity {
    private List<TestDriveListItem> testDriveListItems;
    private TestDriveAdapter testDriveAdapter;
    private ListView testDriveList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_drive_list);
        testDriveList = findViewById(R.id.testDriveList);

        new Thread(() -> {
            ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(GetAllTestDrivesClientCommand.class.getName());
            ((GetAllTestDrivesClientCommand)command).setClient(MainActivity.getClient());
            if (command.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                testDriveListItems =  ((GetAllTestDrivesClientCommand)command).getTestDriveListItems();

                this.runOnUiThread(() -> {
                    testDriveAdapter = new TestDriveAdapter(this, R.layout.test_drive_list_item, testDriveListItems);
                    testDriveList.setAdapter(testDriveAdapter);
                });
            }
        }).start();
    }
}