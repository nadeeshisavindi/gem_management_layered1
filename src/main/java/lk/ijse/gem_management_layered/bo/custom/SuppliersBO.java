//package lk.ijse.gem_management_layered.bo.custom;
//
//import lk.ijse.gem_management_layered.dto.SuppliersDTO;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public interface SuppliersBO {
//    boolean saveSupplier(SuppliersDTO dto) throws SQLException;
//
//    boolean updateSupplier(SuppliersDTO dto) throws SQLException;
//
//    boolean deleteSupplier(int id) throws SQLException;
//
//    SuppliersDTO searchSupplier(int id) throws SQLException;
//
//    List<SuppliersDTO> getAllSuppliers() throws SQLException;
//}
package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.SuppliersDTO;

import java.sql.SQLException;
import java.util.List;

public interface SuppliersBO {
    boolean saveSupplier(SuppliersDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SuppliersDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    SuppliersDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
    List<SuppliersDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;
}