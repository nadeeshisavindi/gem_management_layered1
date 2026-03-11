package lk.ijse.gem_management_layered.entity;

public class OrderDetail {
    private int orderDetailsId;
    private int orderId;
    private int gemId;
    private String gemName;
    private int quantity;
    private double unitPrice;

    public OrderDetail() {}

    public OrderDetail(int gemId, String gemName, int quantity, double unitPrice) {
        this.gemId = gemId;
        this.gemName = gemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderDetail(int orderDetailsId, int orderId, int gemId, String gemName, int quantity, double unitPrice) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.gemId = gemId;
        this.gemName = gemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Getters & Setters
    public int getOrderDetailsId() { return orderDetailsId; }
    public void setOrderDetailsId(int orderDetailsId) { this.orderDetailsId = orderDetailsId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getGemId() { return gemId; }
    public void setGemId(int gemId) { this.gemId = gemId; }
    public String getGemName() { return gemName; }
    public void setGemName(String gemName) { this.gemName = gemName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}

