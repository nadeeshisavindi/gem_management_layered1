package lk.ijse.gem_management_layered.bo.custom.impl;


import lk.ijse.gem_management_layered.bo.custom.CustomerBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.CustomerDAO;
import lk.ijse.gem_management_layered.dto.CustomerDTO;
import lk.ijse.gem_management_layered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {


 //   public class CustomerBOImpl implements CustomerBO {

        CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAOType(DAOFactory.DAOType.CUSTOMER);

        @Override
        public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
            return customerDAO.save(new Customer(dto.getFirstName(), dto.getLastName(), dto.getContact()));
        }

        @Override
        public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
            return customerDAO.delete(id);
        }

        @Override
        public List<CustomerDTO> getAllCustomers() throws SQLException {
            List<CustomerDTO> list = new ArrayList<>();
            for (Customer c : customerDAO.getAll()) {
                list.add(new CustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getContact()));
            }
            return list;
        }

}
