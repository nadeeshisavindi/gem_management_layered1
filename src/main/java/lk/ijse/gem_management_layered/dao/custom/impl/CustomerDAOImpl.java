package lk.ijse.gem_management_layered.dao.custom.impl;


import lk.ijse.gem_management_layered.dao.custom.CustomerDAO;
import lk.ijse.gem_management_layered.entity.Customer;
import lk.ijse.gem_management_layered.entity.Sales;
import lk.ijse.gem_management_layered.util.CRUDUtill;
import lk.ijse.gem_management_layered.entity.Gem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//public class CustomerDAOImpl {

    public class CustomerDAOImpl implements CustomerDAO {

        @Override
        public List<Sales> getAll() throws SQLException {
            ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Customers ORDER BY id", orderId);
            ArrayList<Customer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("contact")
                ));
            }
            return list;
        }

        @Override
        public boolean save(Customer entity) throws SQLException, ClassNotFoundException /*throws SQLException*/ {
            return CRUDUtill.execute(
                    entity.getFirstName(), entity.getLastName(), entity.getContact()
            );
        }

        @Override
        public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
            return false;
        }

        @Override
        public boolean update() {
            // Optional: Implement if needed
            return false;
        }

        @Override
        public boolean delete(String id) throws SQLException, ClassNotFoundException {
            return CRUDUtill.execute(Integer.parseInt(id));
        }

        @Override
        public boolean exits() { return false; }

        @Override
        public Gem search(String id) throws SQLException {
            // Optional: implement if needed
            return null;
        }

}
