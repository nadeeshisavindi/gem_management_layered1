package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import lk.ijse.gem_management_layered.db.DBConnection;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.GemBO;
import lk.ijse.gem_management_layered.dto.GemDTO;

import java.sql.SQLException;
import java.util.List;

public class GemController {

    @FXML private TextField txtGemId;
    @FXML private TextField txtGemName;
    @FXML private TextField txtType;

    @FXML private TableView<GemDTO> tableGem;
    @FXML private TableColumn<GemDTO, Integer> colGemId;
    @FXML private TableColumn<GemDTO, String> colGemName;
    @FXML private TableColumn<GemDTO, String> colType;

    private final GemBO gemBO = (GemBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM);

    @FXML
    public void initialize() {
        colGemId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getGemId()).asObject());
        colGemName.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGemName()));
        colType.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getType()));

        loadGemTable();
        setTableClickListener();
    }

    @FXML
    void saveGem(ActionEvent event) {
        try {
            if(!isValidForSave()) return;

            GemDTO dto = new GemDTO(
                    0,
                    txtGemName.getText().trim(),
                    txtType.getText().trim()
            );

            if(gemBO.saveGem(dto)){
                showAlert(Alert.AlertType.INFORMATION, "Gem Saved Successfully!");
                clearFields();
                loadGemTable();
            }

        } catch (SQLException | ClassNotFoundException e){
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    void updateGem(ActionEvent event){
        try {
            if(!isValidForUpdate()) return;

            GemDTO dto = new GemDTO(
                    Integer.parseInt(txtGemId.getText()),
                    txtGemName.getText().trim(),
                    txtType.getText().trim()
            );

            if(gemBO.updateGem(dto)){
                showAlert(Alert.AlertType.INFORMATION,"Gem Updated Successfully!");
                clearFields();
                loadGemTable();
            }

        } catch (SQLException | ClassNotFoundException e){
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void deleteGem(ActionEvent event){
        try {
            if(txtGemId.getText().isEmpty() || !txtGemId.getText().matches("\\d+")){
                showAlert(Alert.AlertType.ERROR,"Enter a valid Gem ID");
                txtGemId.requestFocus();
                return;
            }

            if(gemBO.deleteGem(txtGemId.getText())){
                showAlert(Alert.AlertType.INFORMATION,"Gem Deleted Successfully!");
                clearFields();
                loadGemTable();
            }

        } catch (SQLException | ClassNotFoundException e){
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }


    @FXML
    public void gemReport(ActionEvent event) {
        try {
            // Load the report template
            InputStream reportStream = getClass().getResourceAsStream("/lk/ijse/gem_management_layered/reports/GemReport.jrxml");

            // Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Get DB connection
            Connection connection = DBConnection.getInstance().getConnection();

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);

            // Show the report in a viewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Report Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    public void gemReset(ActionEvent event) {
        clearFields();
    }

    @FXML
    void searchGem(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            try {
                if(txtGemId.getText().isEmpty() || !txtGemId.getText().matches("\\d+")){
                    showAlert(Alert.AlertType.ERROR,"Enter valid Gem ID");
                    return;
                }

                GemDTO dto = gemBO.searchGem(txtGemId.getText());
                if(dto != null){
                    txtGemName.setText(dto.getGemName());
                    txtType.setText(dto.getType());
                } else {
                    showAlert(Alert.AlertType.INFORMATION,"Gem Not Found!");
                }

            } catch (SQLException | ClassNotFoundException e){
                showAlert(Alert.AlertType.ERROR,e.getMessage());
            }
        }
    }

    private void loadGemTable(){
        try {
            List<GemDTO> list = gemBO.getAllGems();
            tableGem.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e){
            showAlert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    private void clearFields(){
        txtGemId.clear();
        txtGemName.clear();
        txtType.clear();
    }

    private void showAlert(Alert.AlertType type, String msg){
        new Alert(type,msg).showAndWait();
    }

    private void setTableClickListener(){
        tableGem.setOnMouseClicked(event -> {
            GemDTO dto = tableGem.getSelectionModel().getSelectedItem();
            if(dto != null){
                txtGemId.setText(String.valueOf(dto.getGemId()));
                txtGemName.setText(dto.getGemName());
                txtType.setText(dto.getType());
            }
        });
    }

    private boolean isValidForSave(){
        if(txtGemName.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Gem Name required");
            txtGemName.requestFocus();
            return false;
        }
        if(txtType.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Gem Type required");
            txtType.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isValidForUpdate(){
        if(txtGemId.getText().isEmpty() || !txtGemId.getText().matches("\\d+")){
            showAlert(Alert.AlertType.ERROR,"Valid Gem ID required");
            txtGemId.requestFocus();
            return false;
        }
        return isValidForSave();
    }
}