package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.Gem_CutterDAO;
import lk.ijse.gem_management_layered.entity.Gem_Cutter;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gem_CutterDAOImpl implements Gem_CutterDAO {

    @Override
    public List<Gem_Cutter> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Gem_Cutters");

        List<Gem_Cutter> list = new ArrayList<>();

        while(rs.next()){
            list.add(new Gem_Cutter(
                    rs.getInt("cutter_id"),
                    rs.getString("name"),
                    rs.getInt("contact"),
                    rs.getString("address")
            ));
        }

        return list;
    }

    @Override
    public boolean save(Gem_Cutter entity) throws SQLException, ClassNotFoundException {

        return CRUDUtill.execute(
                "INSERT INTO Gem_Cutters(name,contact,address) VALUES (?,?,?)",
                entity.getCutterName(),
                entity.getContact(),
                entity.getAddress()
        );
    }

    @Override
    public boolean update(Gem_Cutter entity) throws SQLException, ClassNotFoundException {

        return CRUDUtill.execute(
                "UPDATE Gem_Cutters SET name=?,contact=?,address=? WHERE cutter_id=?",
                entity.getCutterName(),
                entity.getContact(),
                entity.getAddress(),
                entity.getCutterId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return CRUDUtill.execute(
                "DELETE FROM Gem_Cutters WHERE cutter_id=?",
                Integer.parseInt(id)
        );
    }

    @Override
    public Gem_Cutter search(String id) throws SQLException, ClassNotFoundException {

        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT * FROM Gem_Cutters WHERE cutter_id=?",
                Integer.parseInt(id)
        );

        if(rs.next()){
            return new Gem_Cutter(
                    rs.getInt("cutter_id"),
                    rs.getString("name"),
                    rs.getInt("contact"),
                    rs.getString("address")
            );
        }

        return null;
    }
}