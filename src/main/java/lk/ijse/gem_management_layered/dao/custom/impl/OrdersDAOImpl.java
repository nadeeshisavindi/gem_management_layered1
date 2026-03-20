package lk.ijse.gem_management_layered.dao.custom.impl;

import lk.ijse.gem_management_layered.dao.custom.OrdersDAO;
import lk.ijse.gem_management_layered.dto.OrderDetailDTO;
import lk.ijse.gem_management_layered.dto.OrdersDTO;
import lk.ijse.gem_management_layered.dto.OrdersTableDTO;
import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {

    @Override
    public List<OrdersTableDTO> getAllOrders() throws Exception {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT o.order_id, c.first_name, gc.cutter_name, o.order_date " +
                        "FROM orders o " +
                        "JOIN customers c ON o.customer_id = c.id " +
                        "JOIN gem_cutter gc ON o.gem_cutter_id = gc.cutter_id"
        );
        List<OrdersTableDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new OrdersTableDTO(
                    rs.getInt("order_id"),
                    rs.getString("first_name"),
                    rs.getString("cutter_name"),
                    rs.getDate("order_date").toLocalDate()
            ));
        }
        return list;
    }

    @Override
    public void saveOrder(OrdersDTO order) throws Exception {
        CRUDUtill.execute(
                "INSERT INTO orders(customer_id, gem_cutter_id, order_date) VALUES (?, ?, ?)",
                order.getCustomerId(), order.getGemCutterId(), order.getOrderDate()
        );

        ResultSet rs = CRUDUtill.executeQuery("SELECT LAST_INSERT_ID() AS id");
        int orderId = 0;
        if (rs.next()) orderId = rs.getInt("id");

        for (OrderDetailDTO d : order.getOrderDetails()) {
            CRUDUtill.execute(
                    "INSERT INTO orderdetails(order_id, gem_id, quantity, unit_price) VALUES (?, ?, ?, ?)",
                    orderId, d.getGemId(), d.getQuantity(), d.getUnitPrice()
            );
        }
    }

    @Override
    public OrdersDTO searchOrder(int orderId) throws Exception {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT * FROM orders WHERE order_id=?", orderId
        );
        OrdersDTO order = null;
        if (rs.next()) {
            order = new OrdersDTO(
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("gem_cutter_id"),
                    rs.getDate("order_date").toLocalDate(),
                    getOrderDetails(orderId)
            );
        }
        return order;
    }

    private List<OrderDetailDTO> getOrderDetails(int orderId) throws Exception {
        ResultSet rs = CRUDUtill.executeQuery(
                "SELECT od.order_detail_id, od.gem_id, g.gem_name, od.quantity, od.unit_price " +
                        "FROM orderdetails od " +
                        "JOIN gem g ON od.gem_id = g.gem_id " +
                        "WHERE od.order_id=?", orderId
        );
        List<OrderDetailDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new OrderDetailDTO(
                    rs.getInt("order_detail_id"),  // ← fixed
                    orderId,
                    rs.getInt("gem_id"),
                    rs.getString("gem_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("unit_price")
            ));
        }
        return list;
    }

    @Override
    public void updateOrder(OrdersDTO order) throws Exception {
        CRUDUtill.execute(
                "UPDATE orders SET customer_id=?, gem_cutter_id=?, order_date=? WHERE order_id=?",
                order.getCustomerId(), order.getGemCutterId(), order.getOrderDate(), order.getOrderId()
        );
        CRUDUtill.execute("DELETE FROM orderdetails WHERE order_id=?", order.getOrderId());
        for (OrderDetailDTO d : order.getOrderDetails()) {
            CRUDUtill.execute(
                    "INSERT INTO orderdetails(order_id, gem_id, quantity, unit_price) VALUES (?, ?, ?, ?)",
                    order.getOrderId(), d.getGemId(), d.getQuantity(), d.getUnitPrice()
            );
        }
    }

    @Override
    public void deleteOrder(int orderId) throws Exception {
        CRUDUtill.execute("DELETE FROM orderdetails WHERE order_id=?", orderId);
        CRUDUtill.execute("DELETE FROM orders WHERE order_id=?", orderId);
    }
}