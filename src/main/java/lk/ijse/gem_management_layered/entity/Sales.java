package lk.ijse.gem_management_layered.entity;

public class Sales {
    private int saleId;
    private int orderId;
    private String customerName;
    private String orderStatus;

    // Constructors
    public Sales() {}

    public Sales(int orderId, String customerName, String orderStatus) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
    }

    public Sales(int saleId, int orderId, String customerName, String orderStatus) {
        this.saleId = saleId;
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderStatus = orderStatus;
    }

    // Getters & Setters
    public int getSaleId() { return saleId; }
    public void setSaleId(int saleId) { this.saleId = saleId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}