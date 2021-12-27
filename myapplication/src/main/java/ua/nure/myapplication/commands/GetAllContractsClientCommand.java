package ua.nure.myapplication.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.Contract;
import ua.nure.myapplication.activities.MainActivity;
import ua.nure.myapplication.adapters.ContractListItem;
import utility.CommandsList;

public class GetAllContractsClientCommand extends ClientCommand {
    private List<ContractListItem> contractListItems;
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private List<Contract> contracts;
    private Client client;

    public GetAllContractsClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public List<ContractListItem> getContractListItems() {
        return contractListItems;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        ClientCommand command = MainActivity.getClientCommandsHolder().getCommand(GetAllCarsClientCommand.class.getName());
        contractListItems = new ArrayList<>();
        List<Car> cars;


        try {
            dataOutputStream.writeBytes(CommandsList.GET_ALL_CONTRACTS_COMMAND + "\n" + JsonHelper.covertClientToJson(client) + "\n");
            answer = bufferedReader.readLine();

            if (answer.equals(ClientCommand.POSITIVE_ANSWER)) {
                contracts = JsonHelper.parseJsonIntoContractsList(bufferedReader.readLine());

                if (command.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                    cars = ((GetAllCarsClientCommand)command).getCars();

                    int contractIndex = 0;
                    int contractSize = contracts.size();
                    int carIndex = 0;
                    int carSize = cars.size();
                    Car car;
                    Contract contract;


                    while (contractIndex < contractSize) {
                        contract = contracts.get(contractIndex);
                        carIndex = 0;
                        while (carIndex < carSize) {
                           car = cars.get(carIndex);
                           if (car.getSerialNumber().equals(contract.getCarSerialNumber())){
                               contractListItems.add(new ContractListItem(car.getCarLine(), car.getBrand(), Integer.parseInt(car.getPhotoPath()), car.getPrice()));
                               break;
                           }
                           carIndex++;
                       }
                       contractIndex++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
