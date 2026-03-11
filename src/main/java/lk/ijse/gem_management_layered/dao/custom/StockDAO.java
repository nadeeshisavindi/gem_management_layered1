package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dao.CrudDAO;
import lk.ijse.gem_management_layered.entity.Stock;

import java.sql.SQLException;


public interface StockDAO extends CrudDAO<Stock,Integer> {

    boolean delete(Integer id) throws SQLException;

    Stock search(Integer id) throws SQLException;
}
