package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.entity.Suppliers;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersDAO {
    boolean save(Suppliers supplier) throws SQLException, ClassNotFoundException;
    boolean update(Suppliers supplier) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    Suppliers search(int id) throws SQLException, ClassNotFoundException;
    List<Suppliers> getAll() throws SQLException, ClassNotFoundException;
}