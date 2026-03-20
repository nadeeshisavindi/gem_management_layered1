package lk.ijse.gem_management_layered.dto;

import java.time.LocalDate;

public class StockDTO {
    private int stockId;
    private int gemId;
    private String gemName;
    private int quantity;
    private LocalDate date;

    public StockDTO() {}

    public StockDTO(int gemId, int quantity, LocalDate date) {
        this.gemId = gemId;
        this.quantity = quantity;
        this.date = date;
    }

    public StockDTO(int stockId, int gemId, int quantity, LocalDate date) {
        this.stockId = stockId;
        this.gemId = gemId;
        this.quantity = quantity;
        this.date = date;
    }

    public StockDTO(int stockId, int gemId, String gemName, int quantity, LocalDate date) {
        this.stockId = stockId;
        this.gemId = gemId;
        this.gemName = gemName;
        this.quantity = quantity;
        this.date = date;
    }

    public int getStockId() { return stockId; }
    public void setStockId(int stockId) { this.stockId = stockId; }
    public int getGemId() { return gemId; }
    public void setGemId(int gemId) { this.gemId = gemId; }
    public String getGemName() { return gemName; }
    public void setGemName(String gemName) { this.gemName = gemName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}