package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.bo.SuperBO;
import lk.ijse.gem_management_layered.dto.Gem_CutterDTO;

import java.sql.SQLException;
import java.util.List;

public interface Gem_CutterBO extends SuperBO {

    boolean saveCutter(Gem_CutterDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateCutter(Gem_CutterDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCutter(String id) throws SQLException, ClassNotFoundException;

    Gem_CutterDTO searchCutter(String id) throws SQLException, ClassNotFoundException;

    List<Gem_CutterDTO> getAllCutters() throws SQLException, ClassNotFoundException;
}