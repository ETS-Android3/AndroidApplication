package ua.nure.server.commands;

import java.io.DataOutputStream;
import java.io.IOException;
import ua.nure.domain.entity.Contract;
import ua.nure.server.application.ServerConnection;
import ua.nure.server.database.repository.ContractRepository;
import ua.nure.server.exception.RepositoryException;

public class MakeContractServerCommand extends ServerCommand {
    private Contract contract;

    public MakeContractServerCommand () { }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public void execute() {
        try {
            ContractRepository contractRepository = ServerConnection.getContractRepository();
            DataOutputStream dataOutputStream = ServerConnection.getDataOutputStream();
            if (contract != null) {
                dataOutputStream.writeBytes(ServerCommand.POSITIVE_ANSWER);
                contractRepository.insert(contract);
            } else {
                dataOutputStream.writeBytes(ServerCommand.NEGATIVE_ANSWER);
            }
        } catch (RepositoryException | IOException e) {
            e.printStackTrace();
        }
    }
}
