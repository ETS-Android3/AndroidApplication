package ua.nure.server.database;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.sql.PseudoColumnUsage;
import java.sql.SQLException;
import java.util.List;

import oracle.ons.Cli;
import ua.nure.domain.entity.Car;
import ua.nure.domain.entity.Client;
import ua.nure.domain.entity.Contract;
import ua.nure.domain.entity.TestDrive;
import ua.nure.server.application.Server;
import ua.nure.server.database.repository.CarRepository;
import ua.nure.server.database.repository.ClientRepository;
import ua.nure.server.database.repository.ContractRepository;
import ua.nure.server.database.repository.TestDriveRepository;
import ua.nure.server.exception.ConnectionException;
import ua.nure.server.exception.RepositoryException;

class Conn {
    public static void main(String[] args) throws SQLException, RepositoryException, IOException, ConnectionException {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "VALEK";
        String password = "VK07162002";
        Integer poolsCount = 5;
        ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, password, poolsCount);
        CarRepository carRepository = new CarRepository(connectionPool.getConnection());
        List<Car> cars = carRepository.getAll();
        int i = 0;
        int size = cars.size();
        while (i<size) {
            System.out.println(cars.get(i));
            i++;
        }
    }
}
