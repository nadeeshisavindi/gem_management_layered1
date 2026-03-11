package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.entity.Suppliers;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersDAO {
    boolean save(Suppliers suppliers) throws SQLException;

    boolean update(Suppliers suppliers) throws SQLException;

    boolean delete(int supplierId) throws SQLException, ClassNotFoundException;

    Suppliers search(int supplierId) throws SQLException;

    List<Suppliers> getAll() throws SQLException;
}
