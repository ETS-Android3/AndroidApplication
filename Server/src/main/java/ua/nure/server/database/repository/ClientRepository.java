package ua.nure.server.database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ua.nure.domain.entity.Client;
import ua.nure.server.database.ConnectionPool;
import ua.nure.server.exception.RepositoryException;

public class ClientRepository extends Repository<Client> {
    private static final String INSERT_INTO = "INSERT INTO CLIENT(ID, CNAME, LOGIN, PASS, PHONE_NUMBER) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE CLIENT SET PASS=? WHERE ID=?";
    private static final String SELECT_BY_LOGIN = "SELECT * FROM CLIENT WHERE LOGIN=?";
    private static final String SELECT_ALL = "SELECT * FROM CLIENT";
    private static final String SELECT_BY_ID = "SELECT * FROM CLIENT WHERE ID=?";

    public ClientRepository(ConnectionPool.MyConnection myConnection) {
        super(myConnection);
    }

    public Client getByLogin(String login) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_BY_LOGIN, login)) {
            return toEntityOrNull(resultSet);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    public void update(Client client) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(UPDATE, client.getPassword(), client.getIdentifier())) {
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    public void insert(Client client) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(INSERT_INTO, 12, client.getName(), client.getLogin(), client.getPassword(), client.getPhoneNumber())) {
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    protected Client toEntity(ResultSet resultSet) throws SQLException {
        return new Client(resultSet.getInt("ID"), resultSet.getString("LOGIN"), resultSet.getString("CNAME"),
                resultSet.getString("PASS"), resultSet.getString("PHONE_NUMBER"));
    }

    @Override
    public List<Client> getAll() throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_ALL)) {
            return toEntityList(resultSet);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public Client getById(Integer id) throws RepositoryException {
        try (ResultSet resultSet = myConnection.executeQuery(SELECT_BY_ID, id)) {
            resultSet.next();
            return toEntity(resultSet);
        } catch (SQLException exception) {
            throw new RepositoryException(exception.getMessage());
        }
    }
}
