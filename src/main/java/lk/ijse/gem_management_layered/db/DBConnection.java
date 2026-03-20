package lk.ijse.gem_management_layered.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/gem_management";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}













//package lk.ijse.gem_management_layered.db;
//
//
//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class DBConnection {
//
//    private final String DB_URL = "jdbc:mysql://localhost:3306/gem_management";
//    private final String DB_USERNAME = "root";
//    private final String DB_PASSWORD = "1234";
//
//    private static DBConnection dbc;
//    private Connection connection;
//
//    private DBConnection() throws SQLException {
//        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//    }
//
//    public static DBConnection getInstance() throws SQLException {
//        return (dbc==null) ? dbc=new DBConnection() : dbc;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//}
//












/*
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private final String DB_URL = "jdbc:mysql://localhost:3306/supermarket";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "ijse";

    private static DBConnection dbc;
    private Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static DBConnection getInstance() throws SQLException {
        return (dbc==null) ? dbc=new DBConnection() : dbc;
    }

    public Connection getConnection() {
        return connection;
    }
}*/






//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//
//    private static DBConnection instance;
//    private Connection connection;
//
//    private static final String DB_URL =  "jdbc:mysql://localhost:3306/gem_management";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "1234";
//
//    private DBConnection() throws SQLException {
//        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//    }
//
//    public static DBConnection getInstance() throws SQLException {
//        if (instance == null) {
//            instance = new DBConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//}



//package lk.ijse.gem_management_layered.db;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//
//    private static DBConnection instance;
//    private Connection connection;
//
//    private DBConnection() throws SQLException, ClassNotFoundException {
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/gem_management",
//                "root",
//                "1234"
//        );
//    }
//
//    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
//
//        if(instance == null){
//            instance = new DBConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection(){
//        return connection;
//    }
//}