package lk.ijse.gem_management_layered.entity;
import java.time.LocalDate;
public class Stock {


        private int stockId;
        private int gemId;
        private int quantity;
        private LocalDate date;

        public Stock() {}

        public Stock(int gemId, int quantity, LocalDate date) {
            this.gemId = gemId;
            this.quantity = quantity;
            this.date = date;
        }

        public Stock(int stockId, int gemId, int quantity, LocalDate date) {
            this.stockId = stockId;
            this.gemId = gemId;
            this.quantity = quantity;
            this.date = date;
        }

        public int getStockId() { return stockId; }
        public void setStockId(int stockId) { this.stockId = stockId; }

        public int getGemId() { return gemId; }
        public void setGemId(int gemId) { this.gemId = gemId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }



}
