package json;

import com.google.gson.Gson;

import ua.nure.domain.entity.Client;

public class JsonHelper {
    private static final Gson gson = new Gson();

    public static Client parseJsonIntoClient(String json) {
        return gson.fromJson(json, Client.class);
    }

    public static String CovertClientToJson(Client client) {
        return gson.toJson(client);
    }
}
