package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.entity.Sales;

import java.sql.SQLException;
import java.util.List;

public interface SalesDAO {
    boolean save(Sales sale) throws SQLException, ClassNotFoundException;
    boolean update(Sales sale) throws SQLException, ClassNotFoundException;
    boolean delete(int saleId) throws SQLException, ClassNotFoundException;
    Sales search(int saleId) throws SQLException, ClassNotFoundException;
    List<Sales> getAll() throws SQLException, ClassNotFoundException;
}