package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.SuppliersDTO;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersBO {
    boolean saveSupplier(SuppliersDTO dto) throws SQLException;

    boolean updateSupplier(SuppliersDTO dto) throws SQLException;

    boolean deleteSupplier(int id) throws SQLException;

    SuppliersDTO searchSupplier(int id) throws SQLException;

    List<SuppliersDTO> getAllSuppliers() throws SQLException;
}
