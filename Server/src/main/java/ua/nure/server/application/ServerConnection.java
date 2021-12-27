package ua.nure.server.application;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Locale;
import json.JsonHelper;
import ua.nure.server.commands.ChangePasswordServerCommand;
import ua.nure.server.commands.GetAllCarsServerCommand;
import ua.nure.server.commands.MakeContractServerCommand;
import ua.nure.server.commands.MakeTestDriveServerCommand;
import ua.nure.server.commands.ServerCommand;
import ua.nure.server.commands.GetUserServerCommand;
import ua.nure.server.commands.LoginServerCommand;
import ua.nure.server.commands.RegistrationServerCommand;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.database.repository.CarRepository;
import ua.nure.server.database.repository.ClientRepository;
import ua.nure.server.database.repository.ContractRepository;
import ua.nure.server.database.repository.TestDriveRepository;
import utility.CommandsList;

public class ServerConnection extends Thread {
    private static TestDriveRepository testDriveRepository;
    private static ContractRepository contractRepository;
    private static ClientRepository clientRepository;
    private static CarRepository carRepository;
    private static DataOutputStream dataOutputStream;
    private volatile Boolean isDisconnected = false;
    private static BufferedReader bufferedReader;
    private final Socket socket;

    public ServerConnection(Socket socket) throws IOException {
        ConnectionPool.MyConnection connection = Server.getConnectionPool().getConnection();
        clientRepository = new ClientRepository(connection);
        carRepository = new CarRepository(connection);
        contractRepository = new ContractRepository(connection);
        testDriveRepository = new TestDriveRepository(connection);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
    }

    public static CarRepository getCarRepository() {
        return carRepository;
    }

    public static ClientRepository getClientRepository() {
        return clientRepository;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public static TestDriveRepository getTestDriveRepository() {
        return testDriveRepository;
    }

    public static ContractRepository getContractRepository() {
        return contractRepository;
    }

    private String getConnectionName() {
        return getName().toUpperCase(Locale.ROOT);
    }

    @Override
    public void run() {
        String request;
        try {
            while (!isDisconnected) {
                request = bufferedReader.readLine();
                ServerCommand command;
                switch (request) {
                    case CommandsList.LOGIN_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE STARTED");
                        command = Server.getCommand(LoginServerCommand.class.getName());
                        ((LoginServerCommand)command).setLogin(bufferedReader.readLine());
                        ((LoginServerCommand)command).setPassword(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "LOGIN CASE FINISHED");
                        break;
                    case CommandsList.REGISTRATION_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE STARTED");
                        command = Server.getCommand(RegistrationServerCommand.class.getName());
                        ((RegistrationServerCommand)command).setClient(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "REGISTRATION CASE FINISHED");
                        break;
                    case CommandsList.GET_USER_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "GET USER CASE STARTED");
                        command = Server.getCommand(GetUserServerCommand.class.getName());
                        ((GetUserServerCommand)command).setLogin(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "GET USER CASE FINISHED");
                        break;
                    case CommandsList.CHANGE_PASSWORD_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "CHANGE PASSWORD CASE STARTED");
                        command = Server.getCommand(ChangePasswordServerCommand.class.getName());
                        ((ChangePasswordServerCommand)command).setLogin(bufferedReader.readLine());
                        ((ChangePasswordServerCommand)command).setOldPassword(bufferedReader.readLine());
                        ((ChangePasswordServerCommand)command).setNewPassword(bufferedReader.readLine());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "CHANGE PASSWORD CASE FINISHED");
                        break;
                    case CommandsList.GET_ALL_CARS_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "GET ALL CARS COMMAND CASE STARTED");
                        command = Server.getCommand(GetAllCarsServerCommand.class.getName());
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "GET ALL CARS COMMAND CASE FINISHED");
                        break;
                    case CommandsList.MAKE_CONTRACT_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "MAKE CONTRACT COMMAND CASE STARTED");
                        command = Server.getCommand(MakeContractServerCommand.class.getName());
                        ((MakeContractServerCommand)command).setContract(JsonHelper.parseJsonIntoContract(bufferedReader.readLine()));
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "MAKE CONTRACT COMMAND CASE FINISHED");
                        break;
                    case CommandsList.MAKE_TEST_DRIVE_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "MAKE TEST DRIVE COMMAND CASE STARTED");
                        command = Server.getCommand(MakeTestDriveServerCommand.class.getName());
                        ((MakeTestDriveServerCommand)command).setTestDrive(JsonHelper.parseJsonIntoTestDrive(bufferedReader.readLine()));
                        command.execute();
                        System.out.println("[" + getConnectionName() + "]:" + "MAKE TEST DRIVE COMMAND CASE FINISHED");
                        break;
                    case CommandsList.CLOSE_COMMAND:
                        System.out.println("[" + getConnectionName() + "]:" + "CLOSE CASE STARTED");
                        closeAll();
                        System.out.println("[" + getConnectionName() + "]:" + "CLOSE CASE FINISHED");
                        break;
                }
            }
        }
        catch (Exception exception) { }
    }

    private void closeAll() {
        try {
            socket.close();
            isDisconnected = true;
            bufferedReader.close();
            dataOutputStream.close();
            Server.getConnections().remove(this);
        } catch (Exception exception) {
            System.out.println("[" + getConnectionName() + "]:" + "CLOSING CURRENT THREAD ERROR");
        }
    }
}
