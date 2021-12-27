package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.CarBody;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.Contract;
import ua.nure.domain.entity.Engine;
import ua.nure.domain.entity.TestDrive;

public class JsonHelper {
    private static final Gson gson = new Gson();

    public static Client parseJsonIntoClient(String json) {
        return gson.fromJson(json, Client.class);
    }

    public static String convertCarToJson(Car car) {
        return gson.toJson(car);
    }

    public static Car parseJsonIntoCar(String json) {
       return gson.fromJson(json, Car.class);
    }

    public static Engine parseJsonIntoEngine(String json) {
        return gson.fromJson(json, Engine.class);
    }

    public static String convertEngineToJson(Engine engine) {
        return gson.toJson(engine);
    }

    public static CarBody parseJsonIntoCarBody(String json) {
        return gson.fromJson(json, CarBody.class);
    }

    public static String convertCarBodyToJson(CarBody carBody) {
        return gson.toJson(carBody);
    }

    public static String covertClientToJson(Client client) {
        return gson.toJson(client);
    }

    public static String convertTestDriveToJson(TestDrive testDrive) {
        return gson.toJson(testDrive);
    }

    public static TestDrive parseJsonIntoTestDrive(String json) {
        return gson.fromJson(json, TestDrive.class);
    }

    public static String convertContractToJson(Contract contract) {
        return gson.toJson(contract);
    }

    public static Contract parseJsonIntoContract(String json) {
        return gson.fromJson(json, Contract.class);
    }

    public static String convertContractsListToJson(List<Contract> contracts) {
        return gson.toJson(contracts);
    }

    public static List<Contract> parseJsonIntoContractsList(String json) {
        Type type = new TypeToken<List<Contract>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static String convertTestDrivesListToJson(List<TestDrive> testDrives) {
        return gson.toJson(testDrives);
    }

    public static List<TestDrive> parseJsonIntoTestDrivesList(String json) {
        Type type = new TypeToken<List<TestDrive>>(){}.getType();
        return gson.fromJson(json, type);
    }


}
