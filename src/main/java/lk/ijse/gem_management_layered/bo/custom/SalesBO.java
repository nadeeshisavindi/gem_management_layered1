package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.SalesDTO;

import java.sql.SQLException;
import java.util.List;

public interface SalesBO {
    boolean saveSale(SalesDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSale(SalesDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSale(String saleId) throws SQLException, ClassNotFoundException;
    SalesDTO searchSale(String saleId) throws SQLException, ClassNotFoundException;
    List<SalesDTO> getAllSales() throws SQLException, ClassNotFoundException;
}