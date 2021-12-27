package ua.nure.myapplication.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.Contract;
import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class MakeContractClientCommand extends ClientCommand {
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private Car car;
    private Client client;

    public MakeContractClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        try {
            Contract.ContractBuilder contractBuilder = new Contract.ContractBuilder();
            contractBuilder.setClientIdentifier(client.getIdentifier());
            contractBuilder.setCarSerialNumber(car.getSerialNumber());
            contractBuilder.setDate("2021-12-27");
            dataOutputStream.writeBytes(CommandsList.MAKE_CONTRACT_COMMAND + "\n" + JsonHelper.convertContractToJson(contractBuilder.build()) + "\n");
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
