package lk.ijse.gem_management_layered.controller;



import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.SuppliersBO;
import lk.ijse.gem_management_layered.dto.SuppliersDTO;

import java.sql.SQLException;
import java.util.List;

    public class SuppliersController {

        @FXML private TextField supplierid;
        @FXML private TextField name;
        @FXML private TextField address;
        @FXML private TextField contact;

        @FXML private TableView<SuppliersDTO> tablesupplier;
        @FXML private TableColumn<SuppliersDTO,Integer> colsupplierid;
        @FXML private TableColumn<SuppliersDTO,String> colname;
        @FXML private TableColumn<SuppliersDTO,String> coladdress;
        @FXML private TableColumn<SuppliersDTO,Integer> colcontact;

        private final SuppliersBO supplierBO =
                (SuppliersBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIERS);
        public void initialize(){
            colsupplierid.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getSupplierId()).asObject());
            colname.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
            coladdress.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getAddress()));
            colcontact.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getContact()).asObject());

            loadTable();
            tableClick();
        }

        @FXML
        void saveSupplier(ActionEvent event){
            try {
                SuppliersDTO dto = new SuppliersDTO(name.getText(), address.getText(), Integer.parseInt(contact.getText()));
                if(supplierBO.saveSupplier(dto)){
                    alert("Supplier Saved");
                    loadTable();
                    clear();
                }
            } catch (SQLException e) { e.printStackTrace(); }
        }

        @FXML
        void updateSupplier(ActionEvent event){
            try {
                SuppliersDTO dto = new SuppliersDTO(Integer.parseInt(supplierid.getText()), name.getText(), address.getText(), Integer.parseInt(contact.getText()));
                if(supplierBO.updateSupplier(dto)){
                    alert("Supplier Updated");
                    loadTable();
                    clear();
                }
            } catch (SQLException e) { e.printStackTrace(); }
        }

        @FXML
        void deleteSupplier(ActionEvent event){
            try {
                if(supplierBO.deleteSupplier(Integer.parseInt(supplierid.getText()))){
                    alert("Supplier Deleted");
                    loadTable();
                    clear();
                }
            } catch (SQLException e){ e.printStackTrace(); }
        }

        @FXML
        void searchSupplier(KeyEvent event){
            if(event.getCode()== KeyCode.ENTER){
                try {
                    SuppliersDTO dto = supplierBO.searchSupplier(Integer.parseInt(supplierid.getText()));
                    if(dto!=null){
                        name.setText(dto.getName());
                        address.setText(dto.getAddress());
                        contact.setText(String.valueOf(dto.getContact()));
                    } else alert("Supplier Not Found");
                } catch (SQLException e){ e.printStackTrace(); }
            }
        }

        private void loadTable(){
            try {
                List<SuppliersDTO> list = supplierBO.getAllSuppliers();
                tablesupplier.setItems(FXCollections.observableArrayList(list));
            } catch (SQLException e){ e.printStackTrace(); }
        }

        private void tableClick(){
            tablesupplier.setOnMouseClicked(event -> {
                SuppliersDTO dto = tablesupplier.getSelectionModel().getSelectedItem();
                if(dto!=null){
                    supplierid.setText(String.valueOf(dto.getSupplierId()));
                    name.setText(dto.getName());
                    address.setText(dto.getAddress());
                    contact.setText(String.valueOf(dto.getContact()));
                }
            });
        }

        private void clear(){
            supplierid.clear();
            name.clear();
            address.clear();
            contact.clear();
        }

        private void alert(String msg){
            new Alert(Alert.AlertType.INFORMATION,msg).show();
        }

}
