package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.StockDTO;
import java.sql.SQLException;
import java.util.List;

public interface StockBO {
    boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateStock(StockDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException;
    StockDTO searchStock(String stockId) throws SQLException, ClassNotFoundException;
    List<StockDTO> getAllStock() throws SQLException, ClassNotFoundException;
}