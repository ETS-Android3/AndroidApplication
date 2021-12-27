package ua.nure.server.commands;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.database.repository.CarRepository;
import ua.nure.server.exception.RepositoryException;
import utility.CommandsList;

public class GetAllCarsServerCommand extends ServerCommand {

    public GetAllCarsServerCommand() { }

    @Override
    public void execute() {
        try {
            CarRepository carRepository = ServerConnection.getCarRepository();
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();
            List<Car> cars = carRepository.getAll();
            if (cars.size() > 0) {
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER);
                int index = 0;
                int size = cars.size();
                while (index < size) {
                    dataOutputStream.writeBytes(CommandsList.CAR_STRING + "\n" + JsonHelper.convertCarToJson(cars.get(index)) + "\n");
                    dataOutputStream.writeBytes(CommandsList.ENGINE_STRING + "\n" + JsonHelper.convertEngineToJson(cars.get(index).getEngine()) + "\n");
                    dataOutputStream.writeBytes(CommandsList.CAR_BODY_STRING + "\n" + JsonHelper.convertCarBodyToJson(cars.get(index).getBody()) + "\n");
                    index++;
                }
                dataOutputStream.writeBytes(CommandsList.STOP_COMMAND + "\n");
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
