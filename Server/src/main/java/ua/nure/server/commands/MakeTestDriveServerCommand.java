package ua.nure.server.commands;

import java.io.DataOutputStream;
import java.io.IOException;
import ua.nure.domain.entity.TestDrive;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.database.repository.TestDriveRepository;
import ua.nure.server.exception.RepositoryException;

public class MakeTestDriveServerCommand extends ServerCommand {
    private TestDrive testDrive;

    public MakeTestDriveServerCommand () { }

    public void setTestDrive(TestDrive testDrive) {
        this.testDrive = testDrive;
    }

    @Override
    public void execute() {
        try {
            TestDriveRepository testDriveRepository = ServerConnection.getTestDriveRepository();
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();
            if (testDrive != null) {
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER);
                testDriveRepository.insert(testDrive);
            } else {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}

