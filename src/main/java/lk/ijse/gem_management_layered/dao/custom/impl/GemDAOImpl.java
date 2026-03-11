package lk.ijse.gem_management_layered.dao.custom.impl;



import lk.ijse.gem_management_layered.entity.Sales;
import lk.ijse.gem_management_layered.util.CRUDUtill;
import lk.ijse.gem_management_layered.dao.custom.GemDAO;
import lk.ijse.gem_management_layered.entity.Gem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GemDAOImpl implements GemDAO {



        @Override
        public List<Sales> getAll() {
            ArrayList<Gem> list = new ArrayList<>();

            try {
                ResultSet rs = CRUDUtill.execute();

                while (rs.next()) {
                    list.add(new Gem(
                            rs.getInt("gem_id"),
                            rs.getString("gem_name"),
                            rs.getString("type")
                    ));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }

        @Override
        public boolean save(Gem entity) throws SQLException, ClassNotFoundException {

            return CRUDUtill.execute(
                    entity.getGemName(),
                    entity.getType()
            );
        }

    @Override
    public boolean update(Gem entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
        public boolean update() {
            return false;
        }

        @Override
        public boolean delete(String id) throws SQLException, ClassNotFoundException {

            return CRUDUtill.execute(
                    Integer.parseInt(id)
            );
        }

        @Override
        public boolean exits() {
            return false;
        }

        @Override
        public Gem search(String id) throws SQLException, ClassNotFoundException {

            return null;
        }

}
