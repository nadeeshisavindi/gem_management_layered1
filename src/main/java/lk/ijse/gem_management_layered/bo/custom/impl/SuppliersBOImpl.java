package lk.ijse.gem_management_layered.bo.custom.impl;

import lk.ijse.gem_management_layered.bo.custom.SuppliersBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.SuppliersDAO;
import lk.ijse.gem_management_layered.dto.SuppliersDTO;
import lk.ijse.gem_management_layered.entity.Suppliers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersBOImpl implements SuppliersBO {

    private final SuppliersDAO supplierDAO = (SuppliersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIERS);

    @Override
    public boolean saveSupplier(SuppliersDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Suppliers(dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean updateSupplier(SuppliersDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Suppliers(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }


    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(Integer.parseInt(id));
    }

    @Override
    public SuppliersDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Suppliers s = supplierDAO.search(Integer.parseInt(id));
        if (s != null) return new SuppliersDTO(s.getId(), s.getName(), s.getAddress(), s.getContact());
        return null;
    }

    @Override
    public List<SuppliersDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        List<Suppliers> suppliers = supplierDAO.getAll();
        List<SuppliersDTO> list = new ArrayList<>();
        for (Suppliers s : suppliers) {
            list.add(new SuppliersDTO(s.getId(), s.getName(), s.getAddress(), s.getContact()));
        }
        return list;
    }
}