//package lk.ijse.gem_management_layered.dao.custom;
//
//import lk.ijse.gem_management_layered.dao.CrudDAO;
//import lk.ijse.gem_management_layered.entity.Customer;
//import lk.ijse.gem_management_layered.entity.Gem;
//import lk.ijse.gem_management_layered.entity.Sales;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public interface CustomerDAO extends CrudDAO<Customer, Number> {
//
//
//    List<Sales> getAll() throws SQLException;
//
//    boolean save(Customer customer) throws SQLException, ClassNotFoundException;
//
//    boolean update();
//
//    boolean delete(String id) throws SQLException, ClassNotFoundException;
//
//    boolean exits();
//
//    Gem search(String id) throws SQLException;
//}

package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dao.CrudDAO;
import lk.ijse.gem_management_layered.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {
}