package lk.ijse.gem_management_layered.util;


import java.sql.*;
import lk.ijse.gem_management_layered.db.DBConnection;

public class CRUDUtill {

    public static boolean execute(String sql, Object... params) throws SQLException, ClassNotFoundException {

        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {

                pstm.setObject(i + 1, params[i]);

            }

            return pstm.executeUpdate() > 0;
        }
    }

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {

        Connection conn = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = conn.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {

            pstm.setObject(i + 1, params[i]);

        }

        return pstm.executeQuery();
    }
}

















//
//import lk.ijse.gem_management_layered.db.DBConnection;
//
//import java.sql.*;
//
//public class CRUDUtill {
//
//        public static <T> T execute(Object... params) throws SQLException, ClassNotFoundException {
//
//            Connection connection = DBConnection.getDbConnection().getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            for (int i = 0; i < params.length; i++) {
//                preparedStatement.setObject(i + 1, params[i]);
//            }
//
//            if (sql.startsWith("SELECT")) {
//                return (T) preparedStatement.executeQuery();
//            } else {
//                return (T) (Boolean) (preparedStatement.executeUpdate() > 0);
//            }
//        }
//
//    public static ResultSet executeQuery(String query, int orderId) {
//    }
//
//    public static Connection getConnection() {
//    }
//
//    public static int executeAndReturnGeneratedKey(Connection conn, String s, int customerId, int gemCutterId, Date date) {
//    }
//
//    public static ResultSet executeQuery(String query, String username, String password) {
//    }

//    public static boolean execute(String s, String firstName, String lastName, String contact) {
//    }
//
//    public static ResultSet executeQuery(String query) {
//    }
//
//    public static boolean execute(String s, int i) {
//    }

