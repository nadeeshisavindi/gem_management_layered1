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

        private final SuppliersDAO supplierDAO =
                (SuppliersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIERS);

        @Override
        public boolean saveSupplier(SuppliersDTO dto) throws SQLException {
            return suppliersDAO.save(new Suppliers(dto.getName(), dto.getAddress(), dto.getContact()));
        }


        @Override
        public boolean updateSupplier(SuppliersDTO dto) throws SQLException {
            return suppliersDAO.update(new Suppliers(dto.getSupplierId(), dto.getName(), dto.getAddress(), dto.getContact()));
        }

        @Override
        public boolean deleteSupplier(int id) throws SQLException {
            return suppliersDAO.delete(id);
        }

        @Override
        public SuppliersDTO searchSupplier(int id) throws SQLException {
            Suppliers s = suppliersDAO.search(id);
            if(s != null){
                return new SuppliersDTO(s.getSupplierId(), s.getName(), s.getAddress(), s.getContact());
            }
            return null;
        }

        @Override
        public List<SuppliersDTO> getAllSuppliers() throws SQLException {
            List<Suppliers> list = suppliersDAO.getAll();
            List<SuppliersDTO> dtoList = new ArrayList<>();
            for(Suppliers s : list){
                dtoList.add(new SuppliersDTO(s.getSupplierId(), s.getName(), s.getAddress(), s.getContact()));
            }
            return dtoList;
        }

}
