package lk.ijse.gem_management_layered.dao.custom.impl;


import lk.ijse.gem_management_layered.entity.Sales;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import lk.ijse.gem_management_layered.dao.custom.Gem_CutterDAO;

import lk.ijse.gem_management_layered.entity.Gem;
import lk.ijse.gem_management_layered.entity.Gem_Cutter;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gem_CutterDAOImpl implements Gem_CutterDAO {




        @Override
        public List<Sales> getAll() throws SQLException, ClassNotFoundException {

            ResultSet rs = CRUDUtill.execute();
            ArrayList<Gem_Cutter> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Gem_Cutter(
                        rs.getInt("cutter_id"),
                        rs.getString("cutter_name"),
                        rs.getString("contact"),
                        rs.getString("address")
                ));
            }

            return list;
        }

        @Override
        public boolean save(Gem_Cutter entity) throws SQLException, ClassNotFoundException {
            return CRUDUtill.execute(
                    entity.getCutterName(),
                    entity.getContact(),
                    entity.getAddress()
            );
        }

        @Override
        public boolean update(Gem_Cutter entity) throws SQLException, ClassNotFoundException {
            return CRUDUtill.execute(
                    entity.getCutterName(),
                    entity.getContact(),
                    entity.getAddress(),
                    entity.getCutterId()
            );
        }

        @Override
        public boolean delete(String id) throws SQLException, ClassNotFoundException {
            return CRUDUtill.execute(
                    id
            );
        }

        @Override
        public boolean exits() { return false;

        }

        @Override
        public Gem search(String id) {
            return null;
        }

}
