package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.bo.SuperBO;
import lk.ijse.gem_management_layered.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String text);
}