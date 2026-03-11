//package lk.ijse.gem_management_layered.bo.custom.impl;
package lk.ijse.gem_management_layered.bo.custom.impl;
import lk.ijse.gem_management_layered.bo.custom.UserBO;
import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.dao.custom.UserDAO;
import lk.ijse.gem_management_layered.dao.custom.impl.UserDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(Integer.parseInt(id));
    }

    @Override
    public UserDTO searchUser(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDTO searchUser(int id) throws SQLException {
        return null;
    }



    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        return userDAO.getAll();
    }

    @Override
    public UserDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        return userDAO.checkLogin(username, password);
    }
}





































































//
//
//
//import lk.ijse.gem_management_layered.bo.custom.UserBO;
//import lk.ijse.gem_management_layered.dao.custom.UserDAO;
//import lk.ijse.gem_management_layered.dao.custom.impl.UserDAOImpl;
//import lk.ijse.gem_management_layered.dto.UserDTO;
//
//import java.sql.SQLException;
//import java.util.List;
//
//    public class UserBOImpl implements UserBO {
//
//        private final UserDAO userDAO = new UserDAOImpl();
//
//        @Override
//        public boolean saveUser(UserDTO user) throws SQLException, ClassNotFoundException { return userDAO.save(user); }
//
//        @Override
//        public boolean updateUser(UserDTO user) throws SQLException, ClassNotFoundException { return userDAO.update(user); }
//
//        @Override
//        public boolean deleteUser(int userId) throws SQLException, ClassNotFoundException { return userDAO.delete(userId); }
//
//        @Override
//        public UserDTO searchUser(int userId) throws SQLException { return userDAO.search(userId); }
//
//        @Override
//        public List<UserDTO> getAllUsers() throws SQLException { return userDAO.getAll(); }
//
//        @Override
//        public UserDTO login(String username, String password) throws SQLException { return userDAO.login(username, password); }
//
//}
