package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.SuppliersDAO;
import lk.ijse.gem_management_layered.entity.Suppliers;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersDAOImpl implements SuppliersDAO {

    @Override
    public boolean save(Suppliers supplier) throws SQLException, ClassNotFoundException {
        return  CRUDUtill.execute(
                "INSERT INTO Suppliers (name, address, contact) VALUES (?,?,?)",
                supplier.getName(), supplier.getAddress(), supplier.getContact()
        );
    }

    @Override
    public boolean update(Suppliers supplier) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "UPDATE Suppliers SET name=?, address=?, contact=? WHERE supplier_id=?",
                supplier.getName(), supplier.getAddress(), supplier.getContact(), supplier.getId()
        );
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "DELETE FROM Suppliers WHERE supplier_id=?",
                id
        );
    }

    @Override
    public Suppliers search(int id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT * FROM Suppliers WHERE supplier_id=?",
                id
        );
        if (rs.next()) {
            return new Suppliers(
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("contact")
            );
        }
        return null;
    }

    @Override
    public List<Suppliers> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Suppliers ORDER BY supplier_id");
        List<Suppliers> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Suppliers(
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("contact")
            ));
        }
        return list;
    }
}