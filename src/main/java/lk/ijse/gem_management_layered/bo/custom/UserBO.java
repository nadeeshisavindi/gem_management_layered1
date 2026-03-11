package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.UserDTO;
import java.sql.SQLException;
import java.util.List;

public interface UserBO {
    boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO user) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    UserDTO searchUser(String id) throws SQLException, ClassNotFoundException;

    UserDTO searchUser(int id) throws SQLException;

    List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;
    UserDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException;
}