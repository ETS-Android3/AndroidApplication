package ua.nure.myapplication.commands;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.myapplication.MainActivity;
import utility.CommandsList;

public class GetAllCarsClientCommand extends ClientCommand{
    private final DataOutputStream dataOutputStream;
    private final BufferedReader bufferedReader;
    private List<Car> cars;

    public GetAllCarsClientCommand() {
        dataOutputStream = MainActivity.getDataOutputStream();
        bufferedReader = MainActivity.getBufferedReader();
    }

    public List<Car> getCars(){
        return cars;
    }

    @Override
    public String execute() {
        cars = new ArrayList<>();
        String answer = ClientCommand.NEGATIVE_ANSWER;
        String state;
        try {
            dataOutputStream.writeBytes(CommandsList.GET_ALL_CARS_COMMAND + "\n");
            answer = bufferedReader.readLine();
            Car car = null;

            if (answer.equals(ClientCommand.POSITIVE_ANSWER)) {
                do {
                    state = bufferedReader.readLine();
                    switch (state) {
                        case CommandsList.CAR_STRING:
                            car = JsonHelper.parseJsonIntoCar(bufferedReader.readLine());
                            break;
                        case CommandsList.ENGINE_STRING:
                            assert car != null;
                            car.setEngine(JsonHelper.parseJsonIntoEngine(bufferedReader.readLine()));
                            break;
                        case CommandsList.CAR_BODY_STRING:
                            assert car != null;
                            car.setBody(JsonHelper.parseJsonIntoCarBody(bufferedReader.readLine()));
                             cars.add(car);
                            break;
                    }
                } while (!state.equals(CommandsList.STOP_COMMAND));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
