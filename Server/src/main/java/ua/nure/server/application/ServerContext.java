package ua.nure.server.application;


import ua.nure.server.database.ConnectionPool;
import ua.nure.server.exception.ConnectionException;

public class ServerContext {
    private ConnectionPool connectionPool;

    public ServerContext() { }

    public void start() throws ConnectionException {
        initializeConnectionPoolI();
    }

    private void initializeConnectionPoolI() throws ConnectionException {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "VALEK";
        String password = "VK07162002";
        Integer poolsCount = 5;
        ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, password, poolsCount);
    }

}
