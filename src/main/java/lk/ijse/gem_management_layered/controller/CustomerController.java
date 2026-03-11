package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.CustomerBO;
import lk.ijse.gem_management_layered.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {

    @FXML private TextField txtCustomerId;
    @FXML private TextField txtCustomerFirstName;
    @FXML private TextField txtCustomerLastName;
    @FXML private TextField txtCustomerContact;

    @FXML private TableView<CustomerDTO> tableCustomer;
    @FXML private TableColumn<CustomerDTO,Integer> colCustomerId;
    @FXML private TableColumn<CustomerDTO,String> colFirstName;
    @FXML private TableColumn<CustomerDTO,String> colLastName;
    @FXML private TableColumn<CustomerDTO,String> colContact;

    private final CustomerBO customerBO =
            (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @FXML
    public void initialize() {
        colCustomerId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFirstName.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFirstName()));
        colLastName.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLastName()));
        colContact.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getContact()));

        loadCustomerTable();
        setTableClickListener();
    }

    // ------------------- BUTTON ACTIONS -------------------

    @FXML
    public void saveCustomer(ActionEvent event) {
        try {
            if(!isValidForSave()) return;

            CustomerDTO dto = new CustomerDTO(
                    txtCustomerFirstName.getText().trim(),
                    txtCustomerLastName.getText().trim(),
                    txtCustomerContact.getText().trim()
            );

            if(customerBO.saveCustomer(dto)){
                showAlert(Alert.AlertType.INFORMATION,"Customer Saved Successfully!");
                clearFields();
                loadCustomerTable();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    public void deleteCustomer(ActionEvent event) {
        try {
            if(txtCustomerId.getText().isEmpty() || !txtCustomerId.getText().matches("\\d+")){
                showAlert(Alert.AlertType.ERROR,"Valid Customer ID required");
                txtCustomerId.requestFocus();
                return;
            }

            if(customerBO.deleteCustomer(txtCustomerId.getText())){
                showAlert(Alert.AlertType.INFORMATION,"Customer Deleted Successfully!");
                clearFields();
                loadCustomerTable();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    public void searchCustomer(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(txtCustomerId.getText().isEmpty() || !txtCustomerId.getText().matches("\\d+")){
                showAlert(Alert.AlertType.ERROR,"Enter a valid Customer ID");
                return;
            }

            CustomerDTO dto = customerBO.searchCustomer(txtCustomerId.getText());
            if(dto != null){
                txtCustomerFirstName.setText(dto.getFirstName());
                txtCustomerLastName.setText(dto.getLastName());
                txtCustomerContact.setText(dto.getContact());
            } else {
                showAlert(Alert.AlertType.INFORMATION,"Customer Not Found!");
            }

        }
    }

    // ----------------- UTIL METHODS -------------------

    private void loadCustomerTable(){
        try {
            List<CustomerDTO> list = customerBO.getAllCustomers();
            tableCustomer.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void clearFields(){
        txtCustomerId.clear();
        txtCustomerFirstName.clear();
        txtCustomerLastName.clear();
        txtCustomerContact.clear();
    }

    private void showAlert(Alert.AlertType type, String msg){
        new Alert(type,msg).showAndWait();
    }

    private void setTableClickListener(){
        tableCustomer.setOnMouseClicked(event -> {
            CustomerDTO dto = tableCustomer.getSelectionModel().getSelectedItem();
            if(dto != null){
                txtCustomerId.setText(String.valueOf(dto.getId()));
                txtCustomerFirstName.setText(dto.getFirstName());
                txtCustomerLastName.setText(dto.getLastName());
                txtCustomerContact.setText(dto.getContact());
            }
        });
    }

    // ----------------- VALIDATION -------------------

    private boolean isValidForSave(){

        if(txtCustomerFirstName.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"First Name required");
            txtCustomerFirstName.requestFocus();
            return false;
        }

        if(txtCustomerLastName.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Last Name required");
            txtCustomerLastName.requestFocus();
            return false;
        }

        if(txtCustomerContact.getText().isEmpty() || !txtCustomerContact.getText().matches("\\d{10}")){
            showAlert(Alert.AlertType.ERROR,"Valid 10-digit Contact required");
            txtCustomerContact.requestFocus();
            return false;
        }

        return true;
    }

}