package ua.nure.server.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.processing.SupportedSourceVersion;

import oracle.ons.Cli;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.CarBody;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.Contract;
import ua.nure.domain.entity.Engine;
import ua.nure.domain.entity.TestDrive;
import ua.nure.server.database.repository.ClientRepository;
import ua.nure.server.exception.ConnectionException;
import ua.nure.server.exception.RepositoryException;

class Conn {
    public static void main(String[] args) throws SQLException, RepositoryException {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "VALEK";
        String password = "VK07162002";
        Integer poolsCount = 5;
        ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, password, poolsCount);
        ClientRepository clientRepository = new ClientRepository(connectionPool.getConnection());
        System.out.println(clientRepository.getAll());
    }
}
