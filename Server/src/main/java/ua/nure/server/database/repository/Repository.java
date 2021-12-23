package ua.nure.server.database.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.domain.entity.Entity;
import ua.nure.server.database.ConnectionPool;

public abstract class Repository <T extends Entity> implements IRepository<T> {
    protected final ConnectionPool.MyConnection myConnection;

    public Repository(ConnectionPool.MyConnection myConnection) {
        this.myConnection = myConnection;
    }

    protected abstract T toEntity(ResultSet resultSet) throws SQLException;

    protected List<T> toEntityList(ResultSet resultSet) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (resultSet.next()) {
            entities.add(toEntity(resultSet));
        }
        return entities;
    }

    protected T toEntityOrNull(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? toEntity(resultSet) : null;
    }
}
