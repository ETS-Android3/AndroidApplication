package ua.nure.server.database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.domain.entity.TestDrive;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.exception.ConnectionException;
import ua.nure.server.exception.RepositoryException;

public class TestDriveRepository extends Repository<TestDrive> implements IRepository<TestDrive> {
    private static final String INSERT_INTO = "INSERT INTO TEST_DRIVE(ID, CLIENT_ID, CAR_SERIAL_NUMBER, SCORE) VALUES (TESTDRIVE_SQNC.NEXTVAL,?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM TEST_DRIVE WHERE ID=?";
    private static final String SELECT_ALL = "SELECT * FROM TEST_DRIVE";

    public TestDriveRepository(ConnectionPool.MyConnection myConnection) {
        super(myConnection);
    }

    public void insert(TestDrive testDrive) throws RepositoryException {
        try (ResultSet ignored = myConnection.executeQuery(INSERT_INTO, testDrive.getClientIdentifier(), testDrive.getCarSerialNumber(), testDrive.getScore())) {
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public List<TestDrive> getAll() throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_ALL)) {
            return toEntityList(resultSet);
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public TestDrive getById(Integer id) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_BY_ID, id)) {
            return toEntityOrNull(resultSet);
        } catch (SQLException | ConnectionException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    protected TestDrive toEntity(ResultSet resultSet) throws SQLException {
        return new TestDrive.TestDriveBuilder()
                .setIdentifier(resultSet.getInt("ID"))
                .setClientIdentifier(resultSet.getInt("CLIENT_ID"))
                .setCarSerialNumber(resultSet.getInt("CAR_SERIAL_NUMBER"))
                .setScore(resultSet.getInt("SCORE"))
                .build();
    }
}
