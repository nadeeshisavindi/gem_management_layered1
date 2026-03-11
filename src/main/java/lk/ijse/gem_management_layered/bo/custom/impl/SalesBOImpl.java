package lk.ijse.gem_management_layered.bo.custom.impl;

import lk.ijse.gem_management_layered.bo.custom.SalesBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.SalesDAO;
import lk.ijse.gem_management_layered.dto.SalesDTO;
import lk.ijse.gem_management_layered.entity.Sales;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesBOImpl implements SalesBO {

    private final SalesDAO saleDAO = (SalesDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SALES);

    @Override
    public boolean saveSale(SalesDTO dto) throws SQLException, ClassNotFoundException {
        return saleDAO.save(new Sales(dto.getOrderId(), dto.getCustomerName(), dto.getOrderStatus()));
    }

    @Override
    public boolean updateSale(SalesDTO dto) throws SQLException, ClassNotFoundException {
        return saleDAO.update(new Sales(dto.getSaleId(), dto.getOrderId(), dto.getCustomerName(), dto.getOrderStatus()));
    }


    @Override
    public boolean deleteSale(String saleId) throws SQLException, ClassNotFoundException {
        return saleDAO.delete(Integer.parseInt(saleId));
    }

    @Override
    public SalesDTO searchSale(String saleId) throws SQLException, ClassNotFoundException {
        Sales s = saleDAO.search(Integer.parseInt(saleId));
        if (s != null) return new SalesDTO(s.getSaleId(), s.getOrderId(), s.getCustomerName(), s.getOrderStatus());
        return null;
    }

    @Override
    public List<SalesDTO> getAllSales() throws SQLException, ClassNotFoundException {
        List<Sales> sales = saleDAO.getAll();
        List<SalesDTO> list = new ArrayList<>();
        for (Sales s : sales) {
            list.add(new SalesDTO(s.getSaleId(), s.getOrderId(), s.getCustomerName(), s.getOrderStatus()));
        }
        return list;
    }
}