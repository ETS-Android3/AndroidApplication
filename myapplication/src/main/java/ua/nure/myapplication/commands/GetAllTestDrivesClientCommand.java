package ua.nure.myapplication.commands;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.TestDrive;
import ua.nure.myapplication.activities.MainActivity;
import ua.nure.myapplication.adapters.TestDriveListItem;
import utility.CommandsList;

public class GetAllTestDrivesClientCommand extends ClientCommand {
    private List<TestDriveListItem> testDriveListItems;
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private List<TestDrive> testDrives;
    private Client client;

    public GetAllTestDrivesClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public List<TestDriveListItem> getTestDriveListItems() {
        return testDriveListItems;
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
        testDriveListItems = new ArrayList<>();
        List<Car> cars;

        try {
            dataOutputStream.writeBytes(CommandsList.GET_ALL_TEST_DRIVES_COMMAND + "\n" + JsonHelper.covertClientToJson(client) + "\n");
            answer = bufferedReader.readLine();

            if (answer.equals(ClientCommand.POSITIVE_ANSWER)) {
                testDrives = JsonHelper.parseJsonIntoTestDrivesList(bufferedReader.readLine());
                if (command.execute().equals(ClientCommand.POSITIVE_ANSWER)) {
                    cars = ((GetAllCarsClientCommand)command).getCars();

                    int testDriveIndex = 0;
                    int testDriveSize = testDrives.size();
                    int carIndex = 0;
                    int carSize = cars.size();
                    Car car;
                    TestDrive testDrive;

                    while (testDriveIndex < testDriveSize) {
                        testDrive = testDrives.get(testDriveIndex);
                        carIndex = 0;
                        while (carIndex < carSize) {
                            car = cars.get(carIndex);
                            if (car.getSerialNumber().equals(testDrive.getCarSerialNumber())){
                                testDriveListItems.add(new TestDriveListItem(car.getCarLine(), car.getBrand(), Integer.parseInt(car.getPhotoPath()), testDrive.getScore()));
                                break;
                            }
                            carIndex++;
                        }
                        testDriveIndex++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
