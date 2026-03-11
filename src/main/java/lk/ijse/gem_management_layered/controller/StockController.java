package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.StockBO;
import lk.ijse.gem_management_layered.dto.GemDTO;
import lk.ijse.gem_management_layered.dto.StockDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StockController {

    @FXML private TextField txtStockId;
    @FXML private TextField txtQuantity;
    @FXML private DatePicker dpDate;
    @FXML private ComboBox<GemDTO> cmbGem;

    @FXML private TableView<StockDTO> tableStock;
    @FXML private TableColumn<StockDTO, Integer> colStockId;
    @FXML private TableColumn<StockDTO, Integer> colGemId;
    @FXML private TableColumn<StockDTO, Integer> colQuantity;
    @FXML private TableColumn<StockDTO, LocalDate> colDate;

    private final StockBO stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOType.STOCK);

    @FXML
    public void initialize() {
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        colGemId.setCellValueFactory(new PropertyValueFactory<>("gemId"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        loadStockTable();
        tableClick();
    }

    @FXML
    public void saveStock(ActionEvent event) {
        try {
            if (!isValid()) return;
            GemDTO gem = cmbGem.getSelectionModel().getSelectedItem();
            StockDTO dto = new StockDTO(gem.getGemId(), Integer.parseInt(txtQuantity.getText()), dpDate.getValue());
            if (stockBO.saveStock(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Stock Saved!");
                clearFields();
                loadStockTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void updateStock(ActionEvent event) {
        try {
            if (!isValid() || txtStockId.getText().isEmpty()) return;
            GemDTO gem = cmbGem.getSelectionModel().getSelectedItem();
            StockDTO dto = new StockDTO(Integer.parseInt(txtStockId.getText()), gem.getGemId(), Integer.parseInt(txtQuantity.getText()), dpDate.getValue());
            if (stockBO.updateStock(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Stock Updated!");
                clearFields();
                loadStockTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void deleteStock(ActionEvent event) {
        try {
            if (txtStockId.getText().isEmpty()) return;
            if (stockBO.deleteStock(txtStockId.getText())) {
                showAlert(Alert.AlertType.INFORMATION, "Stock Deleted!");
                clearFields();
                loadStockTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void resetStock(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void searchStock(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                StockDTO dto = stockBO.searchStock(txtStockId.getText());
                if (dto != null) {
                    txtQuantity.setText(String.valueOf(dto.getQuantity()));
                    dpDate.setValue(dto.getDate());
                    // Gem selection handled outside if needed
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Stock Not Found!");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    private void loadStockTable() {
        try {
            List<StockDTO> list = stockBO.getAllStock();
            tableStock.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void tableClick() {
        tableStock.setOnMouseClicked(event -> {
            StockDTO dto = tableStock.getSelectionModel().getSelectedItem();
            if (dto != null) {
                txtStockId.setText(String.valueOf(dto.getStockId()));
                txtQuantity.setText(String.valueOf(dto.getQuantity()));
                dpDate.setValue(dto.getDate());
            }
        });
    }

    private boolean isValid() {
        if (txtQuantity.getText().isEmpty() || !txtQuantity.getText().matches("\\d+")) return false;
        if (dpDate.getValue() == null) return false;
        if (cmbGem.getSelectionModel().getSelectedItem() == null) return false;
        return true;
    }

    private void clearFields() {
        txtStockId.clear();
        txtQuantity.clear();
        dpDate.setValue(null);
        cmbGem.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}