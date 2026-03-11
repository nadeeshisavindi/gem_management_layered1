package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.GemDAO;
import lk.ijse.gem_management_layered.entity.Gem;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GemDAOImpl implements GemDAO {

    @Override
    public boolean save(Gem gem) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute("INSERT INTO Gem (gem_name, type) VALUES (?,?)",
                gem.getGemName(), gem.getType());
    }

    @Override
    public boolean update(Gem gem) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute("UPDATE Gem SET gem_name=?, type=? WHERE gem_id=?",
                gem.getGemName(), gem.getType(), gem.getGemId());
    }

    @Override
    public boolean delete(int gemId) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute("DELETE FROM Gem WHERE gem_id=?", gemId);
    }

    @Override
    public Gem search(int gemId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Gem WHERE gem_id=?", gemId);
        if(rs.next()){
            return new Gem(
                    rs.getInt("gem_id"),
                    rs.getString("gem_name"),
                    rs.getString("type")
            );
        }
        return null;
    }

    @Override
    public List<Gem> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Gem ORDER BY gem_id");
        List<Gem> list = new ArrayList<>();
        while(rs.next()){
            list.add(new Gem(
                    rs.getInt("gem_id"),
                    rs.getString("gem_name"),
                    rs.getString("type")
            ));
        }
        return list;
    }
}