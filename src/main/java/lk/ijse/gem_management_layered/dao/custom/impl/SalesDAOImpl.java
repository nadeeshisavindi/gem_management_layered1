package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.SalesDAO;
import lk.ijse.gem_management_layered.entity.Sales;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDAOImpl implements SalesDAO {

    @Override
    public boolean save(Sales sale) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "INSERT INTO Sales (order_id, customer_name, order_status) VALUES (?,?,?)",
                sale.getOrderId(), sale.getCustomerName(), sale.getOrderStatus()
        );
    }


    @Override
    public boolean update(Sales sale) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "UPDATE Sales SET order_id=?, customer_name=?, order_status=? WHERE sale_id=?",
                sale.getOrderId(), sale.getCustomerName(), sale.getOrderStatus(), sale.getSaleId()
        );
    }

    @Override
    public boolean delete(int saleId) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "DELETE FROM Sales WHERE sale_id=?",
                saleId
        );
    }

    @Override
    public Sales search(int saleId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT * FROM Sales WHERE sale_id=?",
                saleId
        );
        if (rs.next()) {
            return new Sales(
                    rs.getInt("sale_id"),
                    rs.getInt("order_id"),
                    rs.getString("customer_name"),
                    rs.getString("order_status")
            );
        }
        return null;
    }

    @Override
    public List<Sales> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Sales ORDER BY sale_id");
        List<Sales> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Sales(
                    rs.getInt("sale_id"),
                    rs.getInt("order_id"),
                    rs.getString("customer_name"),
                    rs.getString("order_status")
            ));
        }
        return list;
    }
}