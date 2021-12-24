package ua.nure.myapplication.commands;

import java.util.HashMap;
import java.util.Map;

public class ClientCommandService {
    private static final Map<String, ClientCommand> clientCommands = new HashMap<>();

    private ClientCommandService() {
        clientCommands.put(ChangePasswordClientCommand.class.getName(), new ChangePasswordClientCommand());
        clientCommands.put(GetUserClientCommand.class.getName(), new GetUserClientCommand());
        clientCommands.put(LoginClientCommand.class.getName(), new LoginClientCommand());
        clientCommands.put(RegistrationClientCommand.class.getName(), new RegistrationClientCommand());
    }

    public static ClientCommandService getInstance() {
        return ClientCommandService.ClientCommandServiceHolder.INSTANCE;
    }

    private static class ClientCommandServiceHolder {
        private static final ClientCommandService INSTANCE = new ClientCommandService();
    }

    public ClientCommand getCommand(String command) {
        return clientCommands.get(command);
    }
}
