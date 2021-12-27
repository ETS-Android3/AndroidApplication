package ua.nure.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import ua.nure.myapplication.R;
import ua.nure.myapplication.adapters.ContractAdapter;
import ua.nure.myapplication.adapters.ContractListItem;
import ua.nure.myapplication.commands.ClientCommand;
import ua.nure.myapplication.commands.GetAllContractsClientCommand;

public class ContractsListActivity extends AppCompatActivity {
    private List<ContractListItem> contractListItems;
    private ContractAdapter contractAdapter;
    private ListView contractsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracts_list);
        contractsList = findViewById(R.id.contractsList);

        new Thread(() -> {
            ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(GetAllContractsClientCommand.class.getName());
            ((GetAllContractsClientCommand)command).setClient(MainActivity.getClient());
            if (command.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                contractListItems =  ((GetAllContractsClientCommand)command).getContractListItems();

                this.runOnUiThread(() -> {
                    contractAdapter = new ContractAdapter(this, R.layout.contract_list_item, contractListItems);
                    contractsList.setAdapter(contractAdapter);
                });
            }
        }).start();

    }
}