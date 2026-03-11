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

    private final SalesDAO salesDAO =
            (SalesDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SALES);

    @Override
    public boolean saveSale(SalesDTO dto) throws SQLException, ClassNotFoundException {
        return salesDAO.save(
                new Sales(dto.getCancel(), dto.getUpdateInfo(), dto.getReturnInfo())
        );
    }

    @Override
    public boolean updateSale(SalesDTO dto) throws SQLException, ClassNotFoundException {
        return salesDAO.update(
                new Sales(dto.getSaleId(), dto.getCancel(), dto.getUpdateInfo(), dto.getReturnInfo())
        );
    }

    @Override
    public boolean deleteSale(int id) throws SQLException, ClassNotFoundException {
        return salesDAO.delete(id);
    }

    @Override
    public SalesDTO searchSale(int id) throws SQLException, ClassNotFoundException {
        Sales sales = salesDAO.search(id);
        if (sales == null) return null;

        return new SalesDTO(
                sales.getSaleId(),
                sales.getCancel(),
                sales.getUpdateInfo(),
                sales.getReturnInfo()
        );
    }

    @Override
    public List<SalesDTO> getAllSales() throws SQLException, ClassNotFoundException {
        // FIXED: List<Sales> instead of List<Stock>
        List<Sales> list = salesDAO.getAll();

        List<SalesDTO> dtoList = new ArrayList<>();
        for (Sales s : list) {
            dtoList.add(
                    new SalesDTO(
                            s.getSaleId(),
                            s.getCancel(),
                            s.getUpdateInfo(),
                            s.getReturnInfo()
                    )
            );
        }
        return dtoList;
    }
}