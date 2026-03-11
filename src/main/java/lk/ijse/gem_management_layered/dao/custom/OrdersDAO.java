package lk.ijse.gem_management_layered.dao.custom;


import lk.ijse.gem_management_layered.dao.SuperDAO;
import lk.ijse.gem_management_layered.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.List;

    public interface OrdersDAO extends SuperDAO {
        boolean save(OrdersDTO order) throws SQLException;
        boolean update(OrdersDTO order) throws SQLException;
        boolean delete(int orderId) throws SQLException, ClassNotFoundException;
        OrdersDTO search(int orderId) throws SQLException;
        List<OrdersDTO> getAll() throws SQLException;

}
