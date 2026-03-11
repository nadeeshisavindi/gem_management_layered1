package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.StockDAO;
import lk.ijse.gem_management_layered.entity.Gem;
import lk.ijse.gem_management_layered.entity.Sales;
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
                    "INSERT INTO Stock (gem_id,quantity,date) VALUES (?,?,?)",
                    stock.getGemId(),
                    stock.getQuantity(),
                    stock.getDate()
            );
        }

        @Override
        public boolean update(Stock stock) throws SQLException, ClassNotFoundException {

            return CRUDUtill.execute(
                    "UPDATE Stock SET gem_id=?,quantity=?,date=? WHERE stock_id=?",
                    stock.getGemId(),
                    stock.getQuantity(),
                    stock.getDate(),
                    stock.getStockId()
            );
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
    public boolean delete(Integer id) throws SQLException {

            return CRUDUtill.execute(
                    "DELETE FROM Stock WHERE stock_id=?",
                    id
            );
        }

        @Override
        public Stock search(Integer id) throws SQLException {

            ResultSet rs = CRUDUtill.executeQuery(
                    "SELECT * FROM Stock WHERE stock_id=?",
                    id
            );

            if(rs.next()){
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
        public List<Sales> getAll() throws SQLException {

            ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Stock", username, password);

            List<Stock> list = new ArrayList<>();

            while(rs.next()){
                list.add(new Stock(
                        rs.getInt("stock_id"),
                        rs.getInt("gem_id"),
                        rs.getInt("quantity"),
                        rs.getDate("date").toLocalDate()
                ));
            }

            return list;
        }
    
}
