package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.UserDTO;
import java.sql.SQLException;
import java.util.List;
public interface UserBO {


        boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException;
        boolean updateUser(UserDTO user) throws SQLException, ClassNotFoundException;
        boolean deleteUser(int userId) throws SQLException, ClassNotFoundException;
        UserDTO searchUser(int userId) throws SQLException;
        List<UserDTO> getAllUsers() throws SQLException;
        UserDTO login(String username, String password) throws SQLException;

         UserDTO checkLogin(String username, String password);
}
