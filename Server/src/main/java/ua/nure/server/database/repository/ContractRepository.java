package ua.nure.server.database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.domain.entity.Contract;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.exception.ConnectionException;
import ua.nure.server.exception.RepositoryException;

public class ContractRepository extends Repository<Contract> implements IRepository<Contract>{
    private static final String INSERT_INTO = "INSERT INTO CONTRACT(ID, CLIENT_ID, CAR_SERIAL_NUMBER, cDATE) VALUES (CONTR_SQNC.NEXTVAL,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM CONTRACT WHERE ID=?";
    private static final String SELECT_ALL = "SELECT * FROM CONTRACT";

    public ContractRepository(ConnectionPool.MyConnection myConnection) {
        super(myConnection);
    }

    public void insert(Contract contract) throws RepositoryException {
        try (ResultSet ignored = myConnection.executeQuery(INSERT_INTO, contract.getClientIdentifier(), contract.getCarSerialNumber(), contract.getDate())) {
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public List<Contract> getAll() throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_ALL)) {
            return toEntityList(resultSet);
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public Contract getById(Integer id) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_BY_ID, id)) {
            return toEntityOrNull(resultSet);
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    protected Contract toEntity(ResultSet resultSet) throws SQLException {
        return new Contract.ContractBuilder()
                .setIdentifier(resultSet.getInt("ID"))
                .setClientIdentifier(resultSet.getInt("CLIENT_ID"))
                .setCarSerialNumber(resultSet.getInt("CAR_SERIAL_NUMBER"))
                .setDate(resultSet.getString("cDATE"))
                .build();
    }
}
