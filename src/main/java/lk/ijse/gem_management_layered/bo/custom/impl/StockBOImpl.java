package lk.ijse.gem_management_layered.bo.custom.impl;

import lk.ijse.gem_management_layered.bo.custom.StockBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.StockDAO;
import lk.ijse.gem_management_layered.dto.StockDTO;
import lk.ijse.gem_management_layered.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {

        private final StockDAO stockDAO =
                (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STOCKS);

        @Override
        public boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException {

            return stockDAO.save(
                    new Stock(dto.getGemId(),dto.getQuantity(),dto.getDate())
            );
        }

        @Override
        public boolean updateStock(StockDTO dto) throws SQLException, ClassNotFoundException {

            return stockDAO.update(
                    new Stock(dto.getStockId(),dto.getGemId(),dto.getQuantity(),dto.getDate())
            );
        }

        @Override
        public boolean deleteStock(int id) throws SQLException {
            return stockDAO.delete(id);
        }

        @Override
        public StockDTO searchStock(int id) throws SQLException {

            Stock stock = stockDAO.search(id);

            if(stock==null) return null;

            return new StockDTO(
                    stock.getStockId(),
                    stock.getGemId(),
                    stock.getQuantity(),
                    stock.getDate()
            );
        }

        @Override
        public List<StockDTO> getAllStock() throws SQLException, ClassNotFoundException {

            List<Stock> list = stockDAO.getAll();

            List<StockDTO> dtoList = new ArrayList<>();

            for(Stock s : list){
                dtoList.add(
                        new StockDTO(
                                s.getStockId(),
                                s.getGemId(),
                                s.getQuantity(),
                                s.getDate()
                        )
                );
            }

            return dtoList;
        }

}
