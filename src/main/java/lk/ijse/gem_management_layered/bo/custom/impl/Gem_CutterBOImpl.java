package lk.ijse.gem_management_layered.bo.custom.impl;


import lk.ijse.gem_management_layered.bo.custom.Gem_CutterBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.Gem_CutterDAO;
import lk.ijse.gem_management_layered.dto.Gem_CutterDTO;
import lk.ijse.gem_management_layered.entity.Gem_Cutter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gem_CutterBOImpl implements Gem_CutterBO {


        Gem_CutterDAO gemCutterDAO =
                (Gem_CutterDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.GEM_CUTTER);

        @Override
        public boolean saveCutter(Gem_CutterDTO dto) throws SQLException, ClassNotFoundException {

            return gemCutterDAO.save(
                    new Gem_Cutter(
                            dto.getCutterName(),
                            dto.getContact(),
                            dto.getAddress()
                    )
            );
        }

        @Override
        public boolean updateCutter(Gem_CutterDTO dto) throws SQLException, ClassNotFoundException {

            return gemCutterDAO.update(
                    new Gem_Cutter(
                            dto.getCutterId(),
                            dto.getCutterName(),
                            dto.getContact(),
                            dto.getAddress()
                    )
            );
        }

        @Override
        public boolean deleteCutter(String id) throws SQLException, ClassNotFoundException {
            return gemCutterDAO.delete(id);
        }

        @Override
        public List<Gem_CutterDTO> getAllCutters() throws SQLException, ClassNotFoundException {

            List<Gem_CutterDTO> list = new ArrayList<>();

            for (Gem_Cutter cutter : gemCutterDAO.getAll()) {

                list.add(
                        new Gem_CutterDTO(
                                cutter.getCutterId(),
                                cutter.getCutterName(),
                                cutter.getContact(),
                                cutter.getAddress()
                        )
                );
            }

            return list;
        }


}
