package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dao.SuperDAO;
import lk.ijse.gem_management_layered.entity.Gem;

import java.sql.SQLException;
import java.util.List;

public interface GemDAO extends SuperDAO {
    boolean save(Gem gem) throws SQLException, ClassNotFoundException;
    boolean update(Gem gem) throws SQLException, ClassNotFoundException;
    boolean delete(int gemId) throws SQLException, ClassNotFoundException;
    Gem search(int gemId) throws SQLException, ClassNotFoundException;
    List<Gem> getAll() throws SQLException, ClassNotFoundException;
}