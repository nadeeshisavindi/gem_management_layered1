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
import lk.ijse.gem_management_layered.dto.GemDTO;

import java.util.List;




public class GemController {

        @FXML private TextField gemid;
        @FXML private TextField gemname;
        @FXML private TextField type;

        @FXML private TableView<GemDTO> tablegem;
        @FXML private TableColumn<GemDTO, Integer> colgemid;
        @FXML private TableColumn<GemDTO, String> colgemname;
        @FXML private TableColumn<GemDTO, String> coltype;

        // Use BO layer
        private final GemBO gemBO = (GemBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM);

        @FXML
        public void initialize() {
            colgemid.setCellValueFactory(new PropertyValueFactory<>("gemId"));
            colgemname.setCellValueFactory(new PropertyValueFactory<>("gemName"));
            coltype.setCellValueFactory(new PropertyValueFactory<>("type"));

            loadGemTable();
            setTableClickListener();
        }

        // SAVE
        @FXML
        public void gemSave(ActionEvent event) {
            try {
                if (!isValidForSave()) return;

                GemDTO dto = new GemDTO(0, gemname.getText().trim(), type.getText().trim());

                if (gemBO.saveGem(dto)) {
                    showAlert(Alert.AlertType.INFORMATION, "Gem Saved Successfully!");
                    clearFields();
                    loadGemTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to save gem");
            }
        }

        // UPDATE
        @FXML
        public void gemUpdate(ActionEvent event) {
            try {
                if (!isValidForUpdate()) return;

                GemDTO dto = new GemDTO(Integer.parseInt(gemid.getText()), gemname.getText().trim(), type.getText().trim());

                if (gemBO.updateGem(dto)) {
                    showAlert(Alert.AlertType.INFORMATION, "Gem Updated Successfully!");
                    clearFields();
                    loadGemTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to update gem");
            }
        }

        // DELETE
        @FXML
        public void gemDelete(ActionEvent event) {
            try {
                if (gemid.getText().isEmpty() || !gemid.getText().matches("\\d+")) {
                    showAlert(Alert.AlertType.ERROR, "Enter a valid Gem ID to delete");
                    gemid.requestFocus();
                    return;
                }

                if (gemBO.deleteGem(String.valueOf(Integer.parseInt(gemid.getText())))) {
                    showAlert(Alert.AlertType.INFORMATION, "Gem Deleted Successfully!");
                    clearFields();
                    loadGemTable();
                }

            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to delete gem");
            }
        }

        // SEARCH
        @FXML
        public void searchGem(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (gemid.getText().isEmpty() || !gemid.getText().matches("\\d+")) {
                        showAlert(Alert.AlertType.ERROR, "Enter a valid Gem ID");
                        return;
                    }

                    GemDTO dto = gemBO.searchGem(String.valueOf(Integer.parseInt(gemid.getText())));
                    if (dto != null) {
                        gemname.setText(dto.getGemName());
                        type.setText(dto.getType());
                    } else {
                        showAlert(Alert.AlertType.INFORMATION, "Gem Not Found!");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Search Failed");
                }
            }
        }

        // UTIL METHODS
        private void loadGemTable() {
            try {
                List<GemDTO> list = gemBO.getAllGems();
                tablegem.setItems(FXCollections.observableArrayList(list));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void clearFields() {
            gemid.clear();
            gemname.clear();
            type.clear();
        }

        private void showAlert(Alert.AlertType type, String msg) {
            new Alert(type, msg).showAndWait();
        }

        private void setTableClickListener() {
            tablegem.setOnMouseClicked(event -> {
                GemDTO selected = tablegem.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    gemid.setText(String.valueOf(selected.getGemId()));
                    gemname.setText(selected.getGemName());
                    type.setText(selected.getType());
                }
            });
        }

        // VALIDATION
        private boolean isValidForSave() {
            if (gemname.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Gem Name is required");
                gemname.requestFocus();
                return false;
            }
            if (!gemname.getText().matches("[a-zA-Z ]+")) {
                showAlert(Alert.AlertType.ERROR, "Gem Name must contain only letters");
                gemname.requestFocus();
                return false;
            }

            if (type.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Gem Type is required");
                type.requestFocus();
                return false;
            }
            if (!type.getText().matches("[a-zA-Z ]+")) {
                showAlert(Alert.AlertType.ERROR, "Gem Type must contain only letters");
                type.requestFocus();
                return false;
            }

            return true;
        }

        private boolean isValidForUpdate() {
            if (gemid.getText().isEmpty() || !gemid.getText().matches("\\d+")) {
                showAlert(Alert.AlertType.ERROR, "Valid Gem ID is required");
                gemid.requestFocus();
                return false;
            }
            return isValidForSave();
        }

        @FXML
        public void gemReset(ActionEvent event) {
            clearFields();
        }

}
