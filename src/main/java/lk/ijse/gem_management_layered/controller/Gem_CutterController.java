package lk.ijse.gem_management_layered.controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.Gem_CutterBO;
import lk.ijse.gem_management_layered.dto.Gem_CutterDTO;

import java.sql.SQLException;
import java.util.List;


public class Gem_CutterController {
  //  public class GemCutterController {

        @FXML
        private TextField txtCutterId;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtContact;

        @FXML
        private TextField txtAddress;

        @FXML
        private TableView<Gem_CutterDTO> tblCutter;

        @FXML
        private TableColumn<Gem_CutterDTO, String> colId;

        @FXML
        private TableColumn<Gem_CutterDTO, String> colName;

        @FXML
        private TableColumn<Gem_CutterDTO, String> colContact;

        @FXML
        private TableColumn<Gem_CutterDTO, String> colAddress;

        private final Gem_CutterBO gemCutterBO =
                (Gem_CutterBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM_CUTTER);

        public void initialize() {

            colId.setCellValueFactory(new PropertyValueFactory<>("cutterId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("cutterName"));
            colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            loadAllCutters();
        }

        public void btnSaveOnAction(ActionEvent actionEvent) {

            try {

                Gem_CutterDTO dto = new Gem_CutterDTO(
                        Integer.parseInt(txtCutterId.getText()),
                        txtName.getText(),
                        txtContact.getText(),
                        txtAddress.getText()
                );

                boolean isSaved = gemCutterBO.saveCutter(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Cutter Saved").show();
                    loadAllCutters();
                    clearFields();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        public void btnUpdateOnAction(ActionEvent actionEvent) {

            try {

                Gem_CutterDTO dto = new Gem_CutterDTO(
                        Integer.parseInt(txtCutterId.getText()),
                        txtName.getText(),
                        txtContact.getText(),
                        txtAddress.getText()
                );

                boolean isUpdated = gemCutterBO.updateCutter(dto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Cutter Updated").show();
                    loadAllCutters();
                    clearFields();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        public void btnDeleteOnAction(ActionEvent actionEvent) {

            try {

                boolean isDeleted = gemCutterBO.deleteCutter(txtCutterId.getText());

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Cutter Deleted").show();
                    loadAllCutters();
                    clearFields();
                }

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

        private void loadAllCutters() {

            try {

                List<Gem_CutterDTO> list = gemCutterBO.getAllCutters();

                ObservableList<Gem_CutterDTO> observableList =
                        FXCollections.observableArrayList(list);

                tblCutter.setItems(observableList);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void clearFields() {

            txtCutterId.clear();
            txtName.clear();
            txtContact.clear();
            txtAddress.clear();
        }

}
