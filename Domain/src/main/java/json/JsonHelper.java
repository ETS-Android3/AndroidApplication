package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.Client;

public class JsonHelper {
    private static final Gson gson = new Gson();

    public static Client parseJsonIntoClient(String json) {
        return gson.fromJson(json, Client.class);
    }

    public static String ConvertCarsListToJson(List<Car> cars) {
        return gson.toJson(cars);
    }

    public static List<Car> parseJsonIntoCarsList(String json) {
       Type listType = new TypeToken<ArrayList<Car>>(){}.getType();
       return gson.fromJson(json, listType);
    }

    public static String CovertClientToJson(Client client) {
        return gson.toJson(client);
    }
}
