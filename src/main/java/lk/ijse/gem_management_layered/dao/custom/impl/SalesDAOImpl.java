package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.SalesDAO;
import lk.ijse.gem_management_layered.entity.Gem;
import lk.ijse.gem_management_layered.entity.Sales;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDAOImpl implements SalesDAO {

    @Override
    public boolean save(Sales sale) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Sales (cancel, update_info, return_info) VALUES (?,?,?)";
        return CRUDUtill.execute(sql, sale.getCancel(), sale.getUpdateInfo(), sale.getReturnInfo());
    }

    @Override
    public boolean update(Sales sale) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Sales SET cancel=?, update_info=?, return_info=? WHERE sale_id=?";
        return CRUDUtill.execute(sql, sale.getCancel(), sale.getUpdateInfo(), sale.getReturnInfo(), sale.getSaleId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Gem search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Sales search(Integer id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Sales WHERE sale_id=?";
        return CRUDUtill.execute(sql, id);
    }

    @Override
    public Sales search(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Sales WHERE sale_id=?";
        ResultSet rs = CRUDUtill.executeQuery(sql, id);

        if (rs.next()) {
            return new Sales(
                    rs.getInt("sale_id"),
                    rs.getInt("cancel"),
                    rs.getString("update_info"),
                    rs.getString("return_info")
            );
        }
        return null;
    }

    @Override
    public List<Sales> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Sales";
        ResultSet rs = CRUDUtill.executeQuery(sql);

        List<Sales> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Sales(
                    rs.getInt("sale_id"),
                    rs.getInt("cancel"),
                    rs.getString("update_info"),
                    rs.getString("return_info")
            ));
        }
        return list;
    }
}