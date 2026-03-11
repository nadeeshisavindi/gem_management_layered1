package lk.ijse.gem_management_layered.dto;

import java.time.LocalDate;

public class OrdersTableDTO {




        private int orderId;
        private String customerName;
        private String gemCutterName;
        private LocalDate orderDate;

        public OrdersTableDTO() {}

        public OrdersTableDTO(int orderId, String customerName, String gemCutterName, LocalDate orderDate) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.gemCutterName = gemCutterName;
            this.orderDate = orderDate;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getGemCutterName() {
            return gemCutterName;
        }

        public void setGemCutterName(String gemCutterName) {
            this.gemCutterName = gemCutterName;
        }

        public LocalDate getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
        }

}
