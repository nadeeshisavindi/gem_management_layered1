package lk.ijse.gem_management_layered.dto;

import java.time.LocalDate;
import java.util.List;

public class OrdersDTO {
    private int orderId;
    private int customerId;
    private int gemCutterId;
    private LocalDate orderDate;
    private List<OrderDetailDTO> orderDetails;

    public OrdersDTO() {}
    public OrdersDTO(int customerId, int gemCutterId, LocalDate orderDate, List<OrderDetailDTO> orderDetails) {
        this.customerId = customerId;
        this.gemCutterId = gemCutterId;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }
    public OrdersDTO(int orderId, int customerId, int gemCutterId, LocalDate orderDate, List<OrderDetailDTO> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.gemCutterId = gemCutterId;
        this.orderDate = orderDate;
        this.orderDetails = orderDetails;
    }

    // Getters & Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getGemCutterId() { return gemCutterId; }
    public void setGemCutterId(int gemCutterId) { this.gemCutterId = gemCutterId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public List<OrderDetailDTO> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetailDTO> orderDetails) { this.orderDetails = orderDetails; }
}