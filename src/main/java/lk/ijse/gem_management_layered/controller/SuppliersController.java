package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.SuppliersBO;
import lk.ijse.gem_management_layered.dto.SuppliersDTO;

import java.sql.SQLException;
import java.util.List;

public class SuppliersController {

    @FXML private TextField txtSupplierId;
    @FXML private TextField txtName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtContact;

    @FXML private TableView<SuppliersDTO> tableSupplier;
    @FXML private TableColumn<SuppliersDTO, Integer> colSupplierId;
    @FXML private TableColumn<SuppliersDTO, String> colName;
    @FXML private TableColumn<SuppliersDTO, String> colAddress;
    @FXML private TableColumn<SuppliersDTO, Integer> colContact;

    private final SuppliersBO supplierBO = (SuppliersBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIERS);

    @FXML
    public void initialize() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadTable();
        tableClick();
    }

    @FXML
    public void saveSupplier(ActionEvent event) {
        try {
            if (!isValid()) return;

            SuppliersDTO dto = new SuppliersDTO(
                    txtName.getText().trim(),
                    txtAddress.getText().trim(),
                    Integer.parseInt(txtContact.getText())
            );

            if (supplierBO.saveSupplier(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Supplier Saved!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void updateSupplier(ActionEvent event) {
        try {
            if (!isValid() || txtSupplierId.getText().isEmpty()) return;

            SuppliersDTO dto = new SuppliersDTO(
                    Integer.parseInt(txtSupplierId.getText()),
                    txtName.getText().trim(),
                    txtAddress.getText().trim(),
                    Integer.parseInt(txtContact.getText())
            );

            if (supplierBO.updateSupplier(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "Supplier Updated!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void deleteSupplier(ActionEvent event) {
        try {
            if (txtSupplierId.getText().isEmpty()) return;

            if (supplierBO.deleteSupplier(txtSupplierId.getText())) {
                showAlert(Alert.AlertType.INFORMATION, "Supplier Deleted!");
                clearFields();
                loadTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void resetSupplier(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void searchSupplier(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                SuppliersDTO dto = supplierBO.searchSupplier(txtSupplierId.getText());
                if (dto != null) {
                    txtName.setText(dto.getName());
                    txtAddress.setText(dto.getAddress());
                    txtContact.setText(String.valueOf(dto.getContact()));
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Supplier Not Found!");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    private void loadTable() {
        try {
            List<SuppliersDTO> list = supplierBO.getAllSuppliers();
            tableSupplier.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void tableClick() {
        tableSupplier.setOnMouseClicked(event -> {
            SuppliersDTO dto = tableSupplier.getSelectionModel().getSelectedItem();
            if (dto != null) {
                txtSupplierId.setText(String.valueOf(dto.getId()));
                txtName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtContact.setText(String.valueOf(dto.getContact()));
            }
        });
    }

    private boolean isValid() {
        if (!txtName.getText().matches("[A-Za-z ]{3,}")) return false;
        if (txtAddress.getText().trim().isEmpty()) return false;
        if (!txtContact.getText().matches("\\d{10}")) return false;
        return true;
    }

    private void clearFields() {
        txtSupplierId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}