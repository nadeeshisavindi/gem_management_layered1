package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.StockDAO;
import lk.ijse.gem_management_layered.entity.Stock;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {

    @Override
    public boolean save(Stock stock) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "INSERT INTO stock (gem_id, quantity, date) VALUES (?, ?, ?)",
                stock.getGemId(), stock.getQuantity(), stock.getDate()
        );
    }

    @Override
    public boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "UPDATE stock SET gem_id=?, quantity=?, date=? WHERE stock_id=?",
                stock.getGemId(), stock.getQuantity(), stock.getDate(), stock.getStockId()
        );
    }

    @Override
    public boolean delete(int stockId) throws SQLException, ClassNotFoundException {
        return CRUDUtill.execute(
                "DELETE FROM stock WHERE stock_id=?",
                stockId
        );
    }

    @Override
    public Stock search(int stockId) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT s.*, g.gem_name FROM stock s " +
                        "JOIN gem g ON s.gem_id = g.gem_id " +
                        "WHERE s.stock_id=?", stockId
        );
        if (rs.next()) {
            return new Stock(
                    rs.getInt("stock_id"),
                    rs.getInt("gem_id"),
                    rs.getInt("quantity"),
                    rs.getDate("date").toLocalDate()
            );
        }
        return null;
    }

    @Override
    public List<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT s.stock_id, s.gem_id, g.gem_name, s.quantity, s.date " +
                        "FROM stock s JOIN gem g ON s.gem_id = g.gem_id " +
                        "ORDER BY s.stock_id"
        );
        List<Stock> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Stock(
                    rs.getInt("stock_id"),
                    rs.getInt("gem_id"),
                    rs.getString("gem_name"),
                    rs.getInt("quantity"),
                    rs.getDate("date").toLocalDate()
            ));
        }
        return list;
    }
}