package lk.ijse.gem_management_layered.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.GemBO;
import lk.ijse.gem_management_layered.bo.custom.StockBO;
import lk.ijse.gem_management_layered.dto.GemDTO;
import lk.ijse.gem_management_layered.dto.StockDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
public class StockController {


        @FXML
        private TextField stockid;

        @FXML
        private TextField quantity;

        @FXML
        private DatePicker date;

        @FXML
        private ComboBox<GemDTO> gemCombo;

        @FXML
        private TableView<StockDTO> tablestock;

        @FXML
        private TableColumn<StockDTO,Integer> colstockid;

        @FXML
        private TableColumn<StockDTO,Integer> colgemid;

        @FXML
        private TableColumn<StockDTO,Integer> colquantity;

        @FXML
        private TableColumn<StockDTO, LocalDate> coldate;

        private final StockBO stockBO =
                (StockBO) BOFactory.getInstance().getBO(BOFactory.BOType.STOCK);

        private final GemBO gemBO =
                (GemBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM);

        public void initialize(){

            colstockid.setCellValueFactory(new PropertyValueFactory<>("stockId"));
            colgemid.setCellValueFactory(new PropertyValueFactory<>("gemId"));
            colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

            loadStockTable();
            loadGemCombo();
            setTableClick();
        }

        // SAVE
        @FXML
        void stockSave(ActionEvent event) {

            try {

                GemDTO gem = gemCombo.getSelectionModel().getSelectedItem();

                StockDTO dto = new StockDTO(
                        gem.getGemId(),
                        Integer.parseInt(quantity.getText()),
                        date.getValue()
                );

                if(stockBO.saveStock(dto)){
                    alert("Stock Saved");
                    loadStockTable();
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // UPDATE
        @FXML
        void stockUpdate(ActionEvent event) {

            try {

                GemDTO gem = gemCombo.getSelectionModel().getSelectedItem();

                StockDTO dto = new StockDTO(
                        Integer.parseInt(stockid.getText()),
                        gem.getGemId(),
                        Integer.parseInt(quantity.getText()),
                        date.getValue()
                );

                if(stockBO.updateStock(dto)){
                    alert("Stock Updated");
                    loadStockTable();
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // DELETE
        @FXML
        void stockDelete(ActionEvent event) {

            try {

                if(stockBO.deleteStock(Integer.parseInt(stockid.getText()))){
                    alert("Stock Deleted");
                    loadStockTable();
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // SEARCH
        @FXML
        void searchStock(KeyEvent event) {

            if(event.getCode()== KeyCode.ENTER){

                try {

                    StockDTO dto = stockBO.searchStock(
                            Integer.parseInt(stockid.getText())
                    );

                    if(dto!=null){

                        quantity.setText(String.valueOf(dto.getQuantity()));
                        date.setValue(dto.getDate());

                    }else{
                        alert("Stock Not Found");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // LOAD TABLE
        private void loadStockTable(){

            try {

                List<StockDTO> list = stockBO.getAllStock();
                tablestock.setItems(FXCollections.observableArrayList(list));

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // LOAD COMBO
        private void loadGemCombo(){

            try {

                List<GemDTO> list = gemBO.getAllGems();
                gemCombo.setItems(FXCollections.observableArrayList(list));

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // TABLE CLICK
        private void setTableClick(){

            tablestock.setOnMouseClicked(event -> {

                StockDTO dto = tablestock.getSelectionModel().getSelectedItem();

                if(dto!=null){

                    stockid.setText(String.valueOf(dto.getStockId()));
                    quantity.setText(String.valueOf(dto.getQuantity()));
                    date.setValue(dto.getDate());
                }
            });
        }

        // CLEAR
        private void clearFields(){

            stockid.clear();
            quantity.clear();
            date.setValue(null);
            gemCombo.getSelectionModel().clearSelection();
        }

        private void alert(String msg){
            new Alert(Alert.AlertType.INFORMATION,msg).show();
        }

}
