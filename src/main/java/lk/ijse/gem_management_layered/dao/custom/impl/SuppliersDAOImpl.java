package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.SuppliersDAO;
import lk.ijse.gem_management_layered.entity.Suppliers;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersDAOImpl implements SuppliersDAO {

        @Override
        public boolean save(Suppliers suppliers) throws SQLException {
            return CRUDUtill.execute(
                    "INSERT INTO Suppliers (name, address, contact) VALUES (?,?,?)",
                    suppliers.getName(),
                    suppliers.getAddress(),
                    suppliers.getContact()
            );
        }

        @Override
        public boolean update(Suppliers suppliers) throws SQLException {
            return CRUDUtill.execute(
                    "UPDATE Suppliers SET name=?, address=?, contact=? WHERE supplier_id=?",
                    suppliers.getName(),
                    suppliers.getAddress(),
                    suppliers.getContact(),
                    suppliers.getSupplierId()
            );
        }

        @Override
        public boolean delete(int supplierId) throws SQLException, ClassNotFoundException {
            return CRUDUtill.execute(
                    "DELETE FROM Suppliers WHERE supplier_id=?",
                    supplierId
            );
        }

        @Override
        public Suppliers search(int supplierId) throws SQLException {
            ResultSet rs = CRUDUtill.executeQuery(
                    "SELECT * FROM Suppliers WHERE supplier_id=?",
                    supplierId
            );

            if(rs.next()){
                return new Suppliers(
                        rs.getInt("supplier_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("contact")
                );
            }
            return null;
        }

        @Override
        public List<Suppliers> getAll() throws SQLException {
            ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Suppliers ORDER BY supplier_id", username, password);
            List<Suppliers> list = new ArrayList<>();
            while(rs.next()){
                list.add(new Suppliers(
                        rs.getInt("supplier_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("contact")
                ));
            }
            return list;
        }

}
