package lk.ijse.gem_management_layered.dao.custom;

import lk.ijse.gem_management_layered.dto.OrdersDTO;
import lk.ijse.gem_management_layered.dto.OrderDetailDTO;
import lk.ijse.gem_management_layered.dto.OrdersTableDTO;

import java.util.List;

public interface OrdersDAO {
    List<OrdersTableDTO> getAllOrders() throws Exception;
    void saveOrder(OrdersDTO order) throws Exception;
    OrdersDTO searchOrder(int orderId) throws Exception;
    void updateOrder(OrdersDTO order) throws Exception;
    void deleteOrder(int orderId) throws Exception;
}