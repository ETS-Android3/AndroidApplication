package ua.nure.server.database;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.sql.SQLException;

import ua.nure.server.application.Server;
import ua.nure.server.database.repository.ClientRepository;
import ua.nure.server.exception.RepositoryException;

class Conn {
    public static void main(String[] args) throws SQLException, RepositoryException, IOException {
        //Socket socket = new Socket("127.0.0.1", Server.PORT);
        InputStream inputStream = System.in;
       /* Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        int i = 1;
        String req = null;
        while (!socket.isClosed()){
            req = bufferedReader.readLine();
            switch (req) {
                case "CLOSE":
                    dataOutputStream.writeBytes("CLOSE\n");
                    socket.close();
            }
        }*/
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "VALEK";
        String password = "VK07162002";
        Integer poolsCount = 5;
        ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, password, poolsCount);
        ClientRepository clientRepository = new ClientRepository(connectionPool.getConnection());
        System.out.println(clientRepository.getAll());
    }
}
