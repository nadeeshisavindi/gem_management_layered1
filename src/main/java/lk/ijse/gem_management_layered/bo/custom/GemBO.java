package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.bo.SuperBO;
import lk.ijse.gem_management_layered.dto.GemDTO;

import java.sql.SQLException;
import java.util.List;

public interface GemBO extends SuperBO {
    boolean saveGem(GemDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateGem(GemDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteGem(String id) throws SQLException, ClassNotFoundException;
    GemDTO searchGem(String id) throws SQLException, ClassNotFoundException;
    List<GemDTO> getAllGems() throws SQLException, ClassNotFoundException;
}