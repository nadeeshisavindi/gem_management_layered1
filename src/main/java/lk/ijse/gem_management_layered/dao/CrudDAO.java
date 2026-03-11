package lk.ijse.gem_management_layered.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,I> extends SuperDAO {

    List<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T entity) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    boolean delete(I id) throws SQLException, ClassNotFoundException;

    T search(I id) throws SQLException, ClassNotFoundException;
}