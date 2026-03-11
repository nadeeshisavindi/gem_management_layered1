package lk.ijse.gem_management_layered.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gem_management",
                "root",
                "1234"
        );
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {

        if(instance == null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}