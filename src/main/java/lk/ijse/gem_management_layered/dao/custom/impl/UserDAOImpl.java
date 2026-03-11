package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.UserDAO;
import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.entity.User;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "INSERT INTO Users (username, password, role) VALUES (?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "UPDATE Users SET username=?, role=? WHERE id=?",
                user.getUsername(),
                user.getRole(),
                user.getId()
        );
    }

    @Override
    public boolean save(UserDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "DELETE FROM Users WHERE id=?",
                id
        );
    }

    @Override
    public UserDTO search(int id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Users WHERE id=?", id);
        if(rs.next()){
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }
        return null;
    }


    @Override
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Users ORDER BY id");
        List<UserDTO> list = new ArrayList<>();
        while(rs.next()){
            list.add(new UserDTO(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
            ));
        }
        return list;
    }

    @Override
    public UserDTO login(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public User checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT * FROM Users WHERE username=? AND password=?",
                username,
                password
        );
        if(rs.next()){
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }
        return null;
    }
}