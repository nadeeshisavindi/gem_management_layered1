package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.entity.User;

import java.sql.SQLException;
import java.util.List;
public interface UserDAO {

    boolean save(User user) throws SQLException, ClassNotFoundException;

    boolean update(User user) throws SQLException, ClassNotFoundException;

    boolean save(UserDTO user) throws SQLException, ClassNotFoundException;
        boolean update(UserDTO user) throws SQLException, ClassNotFoundException;
        boolean delete(int userId) throws SQLException, ClassNotFoundException;
        UserDTO search(int userId) throws SQLException, ClassNotFoundException;
        List<UserDTO> getAll() throws SQLException, ClassNotFoundException;
        UserDTO login(String username, String password) throws SQLException;

    User checkLogin(String username, String password) throws SQLException, ClassNotFoundException;
}
