package lk.ijse.gem_management_layered.dao.custom.impl;



import lk.ijse.gem_management_layered.dao.custom.OrdersDAO;
import lk.ijse.gem_management_layered.dto.OrdersDTO;
import lk.ijse.gem_management_layered.dto.OrderDetailDTO;

import lk.ijse.gem_management_layered.util.CRUDUtill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class OrdersDAOImpl implements OrdersDAO {

        @Override
        public boolean save(OrdersDTO order) throws SQLException {
            Connection conn = CRUDUtill.getConnection();
            conn.setAutoCommit(false);
            try {
                int orderId = CRUDUtill.executeAndReturnGeneratedKey(
                        conn,
                        "INSERT INTO Orders (customer_id, gem_cutter_id, order_date) VALUES (?,?,?)",
                        order.getCustomerId(),
                        order.getGemCutterId(),
                        java.sql.Date.valueOf(order.getOrderDate())
                );

                for (OrderDetailDTO detail : order.getOrderDetails()) {
                    CRUDUtill.execute(
                            conn,
                            "INSERT INTO OrderDetails (order_id, gem_id, quantity, unit_price) VALUES (?,?,?,?)",
                            orderId,
                            detail.getGemId(),
                            detail.getQuantity(),
                            detail.getUnitPrice()
                    );
                }

                conn.commit();
                return true;
            } catch (SQLException | ClassNotFoundException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }

        @Override
        public boolean update(OrdersDTO order) throws SQLException {
            Connection conn = CRUDUtill.getConnection();
            conn.setAutoCommit(false);
            try {
                boolean updated = CRUDUtill.execute(
                        conn,
                        "UPDATE Orders SET customer_id=?, gem_cutter_id=?, order_date=? WHERE order_id=?",
                        order.getCustomerId(),
                        order.getGemCutterId(),
                        java.sql.Date.valueOf(order.getOrderDate()),
                        order.getOrderId()
                );

                CRUDUtill.execute(conn, "DELETE FROM OrderDetails WHERE order_id=?", order.getOrderId());

                for (OrderDetailDTO detail : order.getOrderDetails()) {
                    CRUDUtill.execute(
                            conn,
                            "INSERT INTO OrderDetails (order_id, gem_id, quantity, unit_price) VALUES (?,?,?,?)",
                            order.getOrderId(),
                            detail.getGemId(),
                            detail.getQuantity(),
                            detail.getUnitPrice()
                    );
                }

                conn.commit();
                return updated;
            } catch (SQLException | ClassNotFoundException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }

        @Override
        public boolean delete(int orderId) throws SQLException, ClassNotFoundException {
            Connection conn = CRUDUtill.getConnection();
            conn.setAutoCommit(false);
            try {
                CRUDUtill.execute(conn, "DELETE FROM OrderDetails WHERE order_id=?", orderId);
                boolean deleted = CRUDUtill.execute(conn, "DELETE FROM Orders WHERE order_id=?", orderId);
                conn.commit();
                return deleted;
            } catch (SQLException | ClassNotFoundException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }

        @Override
        public OrdersDTO search(int orderId) throws SQLException {
            ResultSet rs = CRUDUtill.executeQuery(
                    "SELECT * FROM Orders WHERE order_id=?",
                    orderId
            );

            if (rs.next()) {
                OrdersDTO order = new OrdersDTO();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setGemCutterId(rs.getInt("gem_cutter_id"));
                order.setOrderDate(rs.getDate("order_date").toLocalDate());

                List<OrderDetailDTO> details = new ArrayList<>();
                ResultSet rsDetails = CRUDUtill.executeQuery(
                        "SELECT * FROM OrderDetails WHERE order_id=?",
                        orderId
                );
                while (rsDetails.next()) {
                    details.add(new OrderDetailDTO(
                            rsDetails.getInt("order_detail_id"),
                            rsDetails.getInt("order_id"),
                            rsDetails.getInt("gem_id"),
                            rsDetails.getInt("quantity"),
                            rsDetails.getDouble("unit_price")
                    ));
                }
                order.setOrderDetails(details);
                return order;
            }
            return null;
        }

        @Override
        public List<OrdersDTO> getAll() throws SQLException {
            List<OrdersDTO> list = new ArrayList<>();
            int orderId;
            ResultSet rs = CRUDUtill.executeQuery("SELECT * FROM Orders", orderId);
            while (rs.next()) {
                list.add(search(rs.getInt("order_id")));
            }
            return list;
        }

}
