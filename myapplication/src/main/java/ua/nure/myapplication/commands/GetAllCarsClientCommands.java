package ua.nure.myapplication.commands;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class GetAllCarsClientCommands extends ClientCommand{
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private List cars;

    public GetAllCarsClientCommands() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public List<Car> getCars(){
        return cars;
    }

    @Override
    public String execute() {
        String answer = ClientCommand.NEGATIVE_ANSWER;
        try {
            dataOutputStream.writeBytes(CommandsList.GET_ALL_CARS_COMMAND + "\n");
            answer = bufferedReader.readLine();
            if (answer.equals(ClientCommand.POSITIVE_ANSWER)) {
                cars = JsonHelper.parseJsonIntoCarsList(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
