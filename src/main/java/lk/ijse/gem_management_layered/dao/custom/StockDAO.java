package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.entity.Stock;
import java.sql.SQLException;
import java.util.List;

public interface StockDAO {
    boolean save(Stock stock) throws SQLException, ClassNotFoundException;
    boolean update(Stock stock) throws SQLException, ClassNotFoundException;
    boolean delete(int stockId) throws SQLException, ClassNotFoundException;
    Stock search(int stockId) throws SQLException, ClassNotFoundException;
    List<Stock> getAll() throws SQLException, ClassNotFoundException;
}