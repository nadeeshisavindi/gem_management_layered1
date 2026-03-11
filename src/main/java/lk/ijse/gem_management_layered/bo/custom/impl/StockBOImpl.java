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

    private final StockDAO stockDAO = (StockDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STOCK);

    @Override
    public boolean saveStock(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.save(new Stock(dto.getGemId(), dto.getQuantity(), dto.getDate()));
    }

    @Override
    public boolean updateStock(StockDTO dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(dto.getStockId(), dto.getGemId(), dto.getQuantity(), dto.getDate()));
    }

    @Override
    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(Integer.parseInt(stockId));
    }

    @Override
    public StockDTO searchStock(String stockId) throws SQLException, ClassNotFoundException {
        Stock s = stockDAO.search(Integer.parseInt(stockId));
        if (s != null) return new StockDTO(s.getStockId(), s.getGemId(), s.getQuantity(), s.getDate());
        return null;
    }

    @Override
    public List<StockDTO> getAllStock() throws SQLException, ClassNotFoundException {
        List<Stock> stocks = stockDAO.getAll();
        List<StockDTO> list = new ArrayList<>();
        for (Stock s : stocks) {
            list.add(new StockDTO(s.getStockId(), s.getGemId(), s.getQuantity(), s.getDate()));
        }
        return list;
    }
}