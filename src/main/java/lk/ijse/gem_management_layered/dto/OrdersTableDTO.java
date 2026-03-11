package lk.ijse.gem_management_layered.dto;

import java.time.LocalDate;

public class OrdersTableDTO {
    private int orderId;
    private String customerName;
    private String gemCutterName;
    private LocalDate orderDate;

    public OrdersTableDTO(int orderId, String customerName, String gemCutterName, LocalDate orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.gemCutterName = gemCutterName;
        this.orderDate = orderDate;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getGemCutterName() { return gemCutterName; }
    public LocalDate getOrderDate() { return orderDate; }
}