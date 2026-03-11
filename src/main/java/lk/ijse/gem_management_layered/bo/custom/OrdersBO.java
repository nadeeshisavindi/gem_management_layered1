package lk.ijse.gem_management_layered.bo.custom;

import lk.ijse.gem_management_layered.dto.OrdersDTO;
import lk.ijse.gem_management_layered.dto.OrdersTableDTO;
import java.util.List;

public interface OrdersBO {
    List<OrdersTableDTO> getAllOrders() throws Exception;
    void saveOrder(OrdersDTO order) throws Exception;
    OrdersDTO searchOrder(int orderId) throws Exception;
    void updateOrder(OrdersDTO order) throws Exception;
    void deleteOrder(int orderId) throws Exception;
}