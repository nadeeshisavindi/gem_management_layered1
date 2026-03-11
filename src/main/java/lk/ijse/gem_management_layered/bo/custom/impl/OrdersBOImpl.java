package lk.ijse.gem_management_layered.bo.custom.impl;


import lk.ijse.gem_management_layered.bo.custom.OrdersBO;
import lk.ijse.gem_management_layered.dao.DAOFactory;
import lk.ijse.gem_management_layered.dao.custom.OrdersDAO;
import lk.ijse.gem_management_layered.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

        private final OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERS);

        @Override
        public boolean saveOrder(OrdersDTO order) throws SQLException, ClassNotFoundException {
            return ordersDAO.save(order);
        }

        @Override
        public boolean updateOrder(OrdersDTO order) throws SQLException, ClassNotFoundException {
            return ordersDAO.update(order);
        }

        @Override
        public boolean deleteOrder(int orderId) throws SQLException, ClassNotFoundException {
            return ordersDAO.delete(orderId);
        }

        @Override
        public OrdersDTO searchOrder(int orderId) throws SQLException, ClassNotFoundException {
            return ordersDAO.search(orderId);
        }

        @Override
        public List<OrdersDTO> getAllOrders() throws SQLException, ClassNotFoundException {
            return ordersDAO.getAll();
        }

}
