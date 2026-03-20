package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.CustomerDAO;
import lk.ijse.gem_management_layered.entity.Customer;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM customers");  // ← fixed
        List<Customer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("contact")
            ));
        }
        return list;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "INSERT INTO customers (first_name, last_name, contact) VALUES (?, ?, ?)",
                entity.getFirstName(), entity.getLastName(), entity.getContact()
        );
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "UPDATE customers SET first_name=?, last_name=?, contact=? WHERE id=?",
                entity.getFirstName(), entity.getLastName(), entity.getContact(), entity.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "DELETE FROM customers WHERE id=?",  // ← fixed
                id
        );
    }

    @Override
    public Customer search(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT * FROM customers WHERE id=?",  // ← fixed
                id
        );
        if (rs.next()) {
            return new Customer(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("contact")
            );
        }
        return null;
    }
}