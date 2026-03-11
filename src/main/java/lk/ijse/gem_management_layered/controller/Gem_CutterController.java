package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.Gem_CutterBO;
import lk.ijse.gem_management_layered.dto.Gem_CutterDTO;

import java.sql.SQLException;
import java.util.List;

public class Gem_CutterController {

    @FXML private TextField cutterId;
    @FXML private TextField cutterName;
    @FXML private TextField contact;
    @FXML private TextArea address;

    @FXML private TableView<Gem_CutterDTO> tableCutter;
    @FXML private TableColumn<Gem_CutterDTO,Integer> colCutterId;
    @FXML private TableColumn<Gem_CutterDTO,String> colName;
    @FXML private TableColumn<Gem_CutterDTO,Integer> colContact;
    @FXML private TableColumn<Gem_CutterDTO,String> colAddress;

    private final Gem_CutterBO gemCutterBO =
            (Gem_CutterBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM_CUTTER);

    @FXML
    public void initialize() {
        colCutterId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getCutterId()).asObject());
        colName.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCutterName()));
        colContact.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getContact()).asObject());
        colAddress.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress()));

        loadCutterTable();
        setTableClickListener();
    }

    @FXML
    void saveCutter(ActionEvent event) {
        try {
            if(!isValidForSave()) return;

            Gem_CutterDTO dto = new Gem_CutterDTO(
                    cutterName.getText().trim(),
                    Integer.parseInt(contact.getText()),
                    address.getText().trim()
            );

            if(gemCutterBO.saveCutter(dto)){
                showAlert(Alert.AlertType.INFORMATION,"Cutter Saved Successfully!");
                clearFields();
                loadCutterTable();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void updateCutter(ActionEvent event) {
        try {
            if(!isValidForUpdate()) return;

            Gem_CutterDTO dto = new Gem_CutterDTO(
                    Integer.parseInt(cutterId.getText()),
                    cutterName.getText().trim(),
                    Integer.parseInt(contact.getText()),
                    address.getText().trim()
            );

            if(gemCutterBO.updateCutter(dto)){
                showAlert(Alert.AlertType.INFORMATION,"Cutter Updated Successfully!");
                clearFields();
                loadCutterTable();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void deleteCutter(ActionEvent event) {
        try {
            if(cutterId.getText().isEmpty() || !cutterId.getText().matches("\\d+")){
                showAlert(Alert.AlertType.ERROR,"Enter a valid Cutter ID");
                cutterId.requestFocus();
                return;
            }

            if(gemCutterBO.deleteCutter(cutterId.getText())){
                showAlert(Alert.AlertType.INFORMATION,"Cutter Deleted Successfully!");
                clearFields();
                loadCutterTable();
            }

        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void searchCutter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            try {
                if(cutterId.getText().isEmpty() || !cutterId.getText().matches("\\d+")){
                    showAlert(Alert.AlertType.ERROR,"Enter a valid Cutter ID");
                    return;
                }

                Gem_CutterDTO dto = gemCutterBO.searchCutter(cutterId.getText());
                if(dto != null){
                    cutterName.setText(dto.getCutterName());
                    contact.setText(String.valueOf(dto.getContact()));
                    address.setText(dto.getAddress());
                } else {
                    showAlert(Alert.AlertType.INFORMATION,"Cutter Not Found!");
                }

            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR,e.getMessage());
            }
        }
    }

    // ----------------- UTIL METHODS -------------------

    private void loadCutterTable(){
        try {
            List<Gem_CutterDTO> list = gemCutterBO.getAllCutters();
            tableCutter.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void clearFields(){
        cutterId.clear();
        cutterName.clear();
        contact.clear();
        address.clear();
    }

    private void showAlert(Alert.AlertType type, String msg){
        new Alert(type,msg).showAndWait();
    }

    private void setTableClickListener(){
        tableCutter.setOnMouseClicked(event -> {
            Gem_CutterDTO dto = tableCutter.getSelectionModel().getSelectedItem();
            if(dto != null){
                cutterId.setText(String.valueOf(dto.getCutterId()));
                cutterName.setText(dto.getCutterName());
                contact.setText(String.valueOf(dto.getContact()));
                address.setText(dto.getAddress());
            }
        });
    }

    private boolean isValidForSave(){

        if(cutterName.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Cutter name required");
            cutterName.requestFocus();
            return false;
        }

        if(contact.getText().isEmpty() || !contact.getText().matches("\\d{10}")){
            showAlert(Alert.AlertType.ERROR,"Valid 10-digit contact required");
            contact.requestFocus();
            return false;
        }

        if(address.getText().isEmpty() || address.getText().length() < 5){
            showAlert(Alert.AlertType.ERROR,"Address too short");
            address.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isValidForUpdate(){
        if(cutterId.getText().isEmpty() || !cutterId.getText().matches("\\d+")){
            showAlert(Alert.AlertType.ERROR,"Valid Cutter ID required");
            cutterId.requestFocus();
            return false;
        }
        return isValidForSave();
    }

}