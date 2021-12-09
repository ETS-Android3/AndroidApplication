package ua.nure.server.database;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.ons.Cli;
import ua.nure.domain.entity.Client;
import ua.nure.server.Server;

class Conn {
    public static void main(String[] args) throws IOException, SQLException {
        Client client1 = new Client("vladyslav.kryvenko@nure.ua", "Кривенко Владислав Витальевич", "VK07162002", "+38095509171");

        /*try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "VALEK", "VK07162002")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
                Statement stmt=conn.createStatement();

                ResultSet rs=stmt.executeQuery("select * from teacher");
                while(rs.next())  System.out.println(rs.getInt(1)+"  "+rs.getString(2));
                conn.close();
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
