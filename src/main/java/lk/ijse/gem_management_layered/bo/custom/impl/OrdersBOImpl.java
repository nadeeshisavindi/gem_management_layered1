package lk.ijse.gem_management_layered.bo.custom.impl;

import lk.ijse.gem_management_layered.bo.custom.OrdersBO;
import lk.ijse.gem_management_layered.dao.custom.OrdersDAO;
import lk.ijse.gem_management_layered.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.gem_management_layered.dto.OrdersDTO;
import lk.ijse.gem_management_layered.dto.OrdersTableDTO;

import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    private final OrdersDAO ordersDAO = new OrdersDAOImpl();

    @Override
    public List<OrdersTableDTO> getAllOrders() throws Exception {
        return ordersDAO.getAllOrders();
    }

    @Override
    public void saveOrder(OrdersDTO order) throws Exception {
        ordersDAO.saveOrder(order);
    }

    @Override
    public OrdersDTO searchOrder(int orderId) throws Exception {
        return ordersDAO.searchOrder(orderId);
    }

    @Override
    public void updateOrder(OrdersDTO order) throws Exception {
        ordersDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) throws Exception {
        ordersDAO.deleteOrder(orderId);
    }
}