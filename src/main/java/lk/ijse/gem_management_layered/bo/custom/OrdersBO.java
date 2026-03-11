package lk.ijse.gem_management_layered.bo.custom;


import lk.ijse.gem_management_layered.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.List;

    public interface OrdersBO {
        boolean saveOrder(OrdersDTO order) throws SQLException, ClassNotFoundException;
        boolean updateOrder(OrdersDTO order) throws SQLException, ClassNotFoundException;
        boolean deleteOrder(int orderId) throws SQLException, ClassNotFoundException;
        OrdersDTO searchOrder(int orderId) throws SQLException, ClassNotFoundException;
        List<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException;

}
