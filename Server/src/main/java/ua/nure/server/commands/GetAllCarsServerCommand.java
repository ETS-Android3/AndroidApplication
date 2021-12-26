package ua.nure.server.commands;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import json.JsonHelper;
import ua.nure.domain.entity.Car;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.database.repository.CarRepository;
import ua.nure.server.exception.RepositoryException;

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
                dataOutputStream.writeBytes(JsonHelper.ConvertCarsListToJson(cars) + "\n");
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
