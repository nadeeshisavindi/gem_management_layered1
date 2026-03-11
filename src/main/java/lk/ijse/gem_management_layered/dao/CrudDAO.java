package lk.ijse.gem_management_layered.dao;


import lk.ijse.gem_management_layered.entity.Gem;
import lk.ijse.gem_management_layered.entity.Sales;

import java.sql.SQLException;
import java.util.List;

//public interface CrudDAO<C> {


    public interface CrudDAO<T, I extends Number> extends SuperDAO {

        List<Sales> getAll() throws SQLException, ClassNotFoundException;

        boolean save(T entity) throws SQLException, ClassNotFoundException;

        boolean update(T entity) throws SQLException, ClassNotFoundException;

        boolean delete(String id) throws SQLException, ClassNotFoundException;

        Gem search(String id) throws SQLException, ClassNotFoundException;


}
