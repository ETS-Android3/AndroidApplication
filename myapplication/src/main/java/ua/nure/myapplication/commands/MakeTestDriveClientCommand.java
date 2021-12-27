package ua.nure.myapplication.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.TestDrive;
import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class MakeTestDriveClientCommand extends ClientCommand {
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private Car car;
    private Client client;

    public MakeTestDriveClientCommand() {
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
            TestDrive.TestDriveBuilder testDriveBuilder = new TestDrive.TestDriveBuilder();
            testDriveBuilder.setClientIdentifier(client.getIdentifier());
            testDriveBuilder.setCarSerialNumber(car.getSerialNumber());
            testDriveBuilder.setScore(7);
            dataOutputStream.writeBytes(CommandsList.MAKE_TEST_DRIVE_COMMAND + "\n" + JsonHelper.convertTestDriveToJson(testDriveBuilder.build()) + "\n");
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

}
