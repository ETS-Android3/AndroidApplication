package ua.nure.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

import javax.annotation.processing.SupportedSourceVersion;

import ua.nure.domain.entity.Client;
import ua.nure.server.exception.ConnectionException;

public class ConnectionPool {
    private final MyConnection[] pool;

    public ConnectionPool(String DBUrl, String DBUser, String password, Integer count) throws ConnectionException {
        pool = new MyConnection[count];
        try {
            for (int i = 0; i < count; i++){
                pool[i] = new MyConnection(DriverManager.getConnection(DBUrl, DBUser, password));
                System.out.println("Connected");
            }
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }

    public MyConnection getConnection(){
        while (true){
            for (MyConnection connection : pool){
               synchronized (connection){
                   if (connection.isFree){
                       connection.isFree = false;
                       return connection;
                   }
               }
            }
        }
    }

    public static class MyConnection implements AutoCloseable {
        private final Connection connection;
        private Boolean isFree;

        public MyConnection(Connection connection) {
            this.connection = connection;
            this.isFree = true;
        }

        public void commit() throws ConnectionException {
            try {
                connection.commit();
            } catch (SQLException exception){
                throw new ConnectionException(exception.getMessage());
            }
        }

        public void disableAutoCommit() throws ConnectionException {
            try {
                connection.setAutoCommit(false);
            } catch (SQLException exception){
                throw new ConnectionException(exception.getMessage());
            }
        }

        public void rollback() throws ConnectionException {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getMessage());
            }
        }

        @Override
        public void close() throws ConnectionException {
            try {

                connection.setAutoCommit(true);
            } catch (SQLException exception){
                throw new ConnectionException(exception.getMessage());
            }
            isFree = true;
        }

        private PreparedStatement prepareStatement(String sqlCommand, Object[] arguments) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i] == null) {
                    System.out.print("null,");
                    preparedStatement.setNull(i + 1, Types.NULL);
                } else {
                    System.out.print(arguments[i].toString() + ",");
                    preparedStatement.setString(i + 1, arguments[i].toString());
                }
             }
            return preparedStatement;
        }

        public ResultSet executeQuery(String sqlCommand, Object... args) throws ConnectionException {
            try {
                PreparedStatement preparedStatement = prepareStatement(sqlCommand, args);
                return preparedStatement.executeQuery();
            } catch (SQLException ex) {
                throw new ConnectionException(ex.getMessage());
            }
        }
    }
}
