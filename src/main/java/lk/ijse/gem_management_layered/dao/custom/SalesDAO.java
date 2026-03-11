package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dao.CrudDAO;
import lk.ijse.gem_management_layered.entity.Sales;

import java.sql.SQLException;


public interface SalesDAO extends CrudDAO<Sales,Integer> {

    boolean delete(Integer id) throws SQLException, ClassNotFoundException;

    Sales search(Integer id) throws SQLException;

    boolean delete(int id) throws SQLException, ClassNotFoundException;

    Sales search(int id) throws SQLException, ClassNotFoundException;
}
