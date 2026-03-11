package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.SalesBO;
import lk.ijse.gem_management_layered.dto.SalesDTO;

import java.sql.SQLException;
import java.util.List;

public class SalesController {

    @FXML private TextField txtSaleId;
    @FXML private TextField txtOrderId;
    @FXML private TextField txtCustomerName;
    @FXML private ComboBox<String> cmbOrderStatus;

    @FXML private TableView<SalesDTO> tableSales;
    @FXML private TableColumn<SalesDTO, Integer> colSaleId;
    @FXML private TableColumn<SalesDTO, Integer> colOrderId;
    @FXML private TableColumn<SalesDTO, String> colCustomerName;
    @FXML private TableColumn<SalesDTO, String> colOrderStatus;

    private final SalesBO saleBO = (SalesBO) BOFactory.getInstance().getBO(BOFactory.BOType.SALES);

    @FXML
    public void initialize() {
        colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colOrderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        cmbOrderStatus.setItems(FXCollections.observableArrayList("Pending", "Completed", "Cancelled"));

        loadTable();
        tableClick();
    }

    @FXML
    public void saveSale(ActionEvent event) {
        try {
            if (!isValid()) return;

            SalesDTO dto = new SalesDTO(
                    Integer.parseInt(txtOrderId.getText()),
                    txtCustomerName.getText(),
                    cmbOrderStatus.getValue()
            );

            if (saleBO.saveSale(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Sale Saved!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void updateSale(ActionEvent event) {
        try {
            if (!isValid() || txtSaleId.getText().isEmpty()) return;

            SalesDTO dto = new SalesDTO(
                    Integer.parseInt(txtSaleId.getText()),
                    Integer.parseInt(txtOrderId.getText()),
                    txtCustomerName.getText(),
                    cmbOrderStatus.getValue()
            );

            if (saleBO.updateSale(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Sale Updated!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void deleteSale(ActionEvent event) {
        try {
            if (txtSaleId.getText().isEmpty()) return;

            if (saleBO.deleteSale(txtSaleId.getText())) {
                showAlert(Alert.AlertType.INFORMATION, "Sale Deleted!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void resetSale(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void searchSale(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                SalesDTO dto = saleBO.searchSale(txtSaleId.getText());
                if (dto != null) {
                    txtOrderId.setText(String.valueOf(dto.getOrderId()));
                    txtCustomerName.setText(dto.getCustomerName());
                    cmbOrderStatus.setValue(dto.getOrderStatus());
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Sale Not Found!");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    private void loadTable() {
        try {
            List<SalesDTO> list = saleBO.getAllSales();
            tableSales.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void tableClick() {
        tableSales.setOnMouseClicked(event -> {
            SalesDTO dto = tableSales.getSelectionModel().getSelectedItem();
            if (dto != null) {
                txtSaleId.setText(String.valueOf(dto.getSaleId()));
                txtOrderId.setText(String.valueOf(dto.getOrderId()));
                txtCustomerName.setText(dto.getCustomerName());
                cmbOrderStatus.setValue(dto.getOrderStatus());
            }
        });
    }

    private boolean isValid() {
        if (txtOrderId.getText().isEmpty() || !txtOrderId.getText().matches("\\d+")) return false;
        if (txtCustomerName.getText().trim().length() < 2) return false;
        if (cmbOrderStatus.getValue() == null || cmbOrderStatus.getValue().isEmpty()) return false;
        return true;
    }

    private void clearFields() {
        txtSaleId.clear();
        txtOrderId.clear();
        txtCustomerName.clear();
        cmbOrderStatus.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}