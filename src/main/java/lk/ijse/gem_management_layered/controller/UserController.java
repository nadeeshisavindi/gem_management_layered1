//package lk.ijse.gem_management_layered.controller;

package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.UserBO;
import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.util.UserSession;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    @FXML private TextField txtUserId;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbRole;

    @FXML private TableView<UserDTO> tableUser;
    @FXML private TableColumn<UserDTO, Integer> colUserId;
    @FXML private TableColumn<UserDTO, String> colUsername;
    @FXML private TableColumn<UserDTO, String> colRole;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    @FXML
    public void initialize() {
        cmbRole.getItems().addAll("OWNER", "ADMIN", "CASHIER");

        colUserId.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getUserId()).asObject());
        colUsername.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getUsername()));
        colRole.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRole()));

        loadUserTable();
        setTableClickListener();
    }

    @FXML
    public void saveUser(ActionEvent event) {
        try {
            if (!isValidForSave()) return;

            UserDTO dto = new UserDTO(
                    txtUsername.getText(),
                    txtPassword.getText(),
                    cmbRole.getValue()
            );

            if (userBO.saveUser(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "User Saved!");
                clearFields();
                loadUserTable();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void updateUser(ActionEvent event) {
        try {
            if (txtUserId.getText().isEmpty() || !txtUserId.getText().matches("\\d+")) {
                showAlert(Alert.AlertType.ERROR, "Enter valid User ID");
                return;
            }

            UserDTO dto = new UserDTO(
                    Integer.parseInt(txtUserId.getText()),
                    txtUsername.getText(),
                    txtPassword.getText(),
                    cmbRole.getValue()
            );

            if (userBO.updateUser(dto)) {
                showAlert(Alert.AlertType.INFORMATION, "User Updated!");
                clearFields();
                loadUserTable();
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void deleteUser(ActionEvent event) {
        try {
            if (txtUserId.getText().isEmpty() || !txtUserId.getText().matches("\\d+")) {
                showAlert(Alert.AlertType.ERROR, "Enter valid User ID");
                return;
            }

            if (userBO.deleteUser(txtUserId.getText())) {
                showAlert(Alert.AlertType.INFORMATION, "User Deleted!");
                clearFields();
                loadUserTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    public void resetUser(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void searchUser(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                if (txtUserId.getText().isEmpty() || !txtUserId.getText().matches("\\d+")) return;

                UserDTO dto = userBO.searchUser(txtUserId.getText());
                if (dto != null) {
                    txtUsername.setText(dto.getUsername());
                    txtPassword.setText(dto.getPassword());
                    cmbRole.setValue(dto.getRole());
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "User Not Found");
                }
            } catch (SQLException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    private void loadUserTable() {
        try {
            List<UserDTO> list = userBO.getAllUsers();
            tableUser.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTableClickListener() {
        tableUser.setOnMouseClicked(event -> {
            UserDTO dto = tableUser.getSelectionModel().getSelectedItem();
            if (dto != null) {
                txtUserId.setText(String.valueOf(dto.getUserId()));
                txtUsername.setText(dto.getUsername());
                txtPassword.setText(dto.getPassword());
                cmbRole.setValue(dto.getRole());
            }
        });
    }

    private boolean isValidForSave() {
        if (txtUsername.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Username required");
            return false;
        }
        if (txtPassword.getText().length() < 4) {
            showAlert(Alert.AlertType.ERROR, "Password must be >=4 characters");
            return false;
        }
        if (cmbRole.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Select a role");
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
        cmbRole.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}
















































































//
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import lk.ijse.gem_management_layered.bo.custom.UserBO;
//import lk.ijse.gem_management_layered.bo.custom.impl.UserBOImpl;
//import lk.ijse.gem_management_layered.dto.UserDTO;
//
//import java.util.List;
//
//public class UserController {
//
//    @FXML
//    private TextField userid, username;
//    @FXML
//    private PasswordField password;
//    @FXML
//    private ComboBox<String> role;
//    @FXML
//    private TableView<UserDTO> tableuser;
//    @FXML
//    private TableColumn<UserDTO, Integer> tableuserid;
//    @FXML
//    private TableColumn<UserDTO, String> tableusername;
//    @FXML
//    private TableColumn<UserDTO, String> tablerole;
//
//    private final UserBO userBO = new UserBOImpl();
//
//    @FXML
//    public void initialize() {
//        role.getItems().addAll("OWNER", "ADMIN", "CASHIER");
//
//        tableuserid.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getUserId()).asObject());
//        tableusername.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getUsername()));
//        tablerole.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRole()));
//
//        loadUserTable();
//    }
//
//    @FXML
//    private void userSave(ActionEvent event) {
//        try {
//            UserDTO dto = new UserDTO(username.getText(), password.getText(), role.getValue());
//            if (userBO.saveUser(dto)) loadUserTable();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadUserTable() {
//        try {
//            List<UserDTO> list = userBO.getAllUsers();
//            tableuser.setItems(FXCollections.observableArrayList(list));
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//}
////}
////
////import javafx.fxml.FXML;
////import javafx.scene.control.Button;
////import javafx.scene.layout.AnchorPane;
////import javafx.scene.Parent;
////import lk.ijse.gem_management_layered.App;
////import lk.ijse.gem_management_layered.dto.UserDTO;
////import lk.ijse.gem_management_layered.util.UserSession;
////
////import java.io.IOException;
////
////    public class UserController {
////
////        @FXML private AnchorPane mainContent;
////
////        @FXML private Button btnUsers;
////        @FXML private Button btnCustomers;
////        @FXML private Button btnGem;
////        @FXML private Button btnStock;
////        @FXML private Button btnSuppliers;
////        @FXML private Button btnGemCutter;
////        @FXML private Button btnOrders;
////        @FXML private Button btnSales;
////        @FXML private Button btnLogout;
////
////        @FXML
////        public void initialize() {
////            applyRolePermissions();
////        }
////
////        private void applyRolePermissions() {
////            UserDTO user = UserSession.getUser();
////            if(user == null) return;
////
////            String role = user.getRole().toUpperCase();
////
////            switch(role){
////                case "OWNER":
////                    // Full access → do nothing
////                    break;
////                case "ADMIN":
////                    btnUsers.setDisable(true); // Admin cannot manage users
////                    break;
////                case "CASHIER":
////                    btnUsers.setDisable(true);
////                    btnSuppliers.setDisable(true);
////                    btnGem.setDisable(true);
////                    btnStock.setDisable(true);
////                    btnGemCutter.setDisable(true);
////                    btnOrders.setDisable(true);
////                    break;
////                default:
////                    btnUsers.setDisable(true);
////                    btnCustomers.setDisable(true);
////                    btnGem.setDisable(true);
////                    btnStock.setDisable(true);
////                    btnSuppliers.setDisable(true);
////                    btnGemCutter.setDisable(true);
////                    btnOrders.setDisable(true);
////                    btnSales.setDisable(true);
////            }
////        }
////
////        // ================= Navigation =================
////        @FXML
////        private void clickUsers() throws IOException {
////            Parent fxml = App.loadFXML("User");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickCustomers() throws IOException {
////            Parent fxml = App.loadFXML("Customers");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickGem() throws IOException {
////            Parent fxml = App.loadFXML("Gem");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickStock() throws IOException {
////            Parent fxml = App.loadFXML("Stock");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickSuppliers() throws IOException {
////            Parent fxml = App.loadFXML("Suppliers");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickGemCutter() throws IOException {
////            Parent fxml = App.loadFXML("Gem_Cutter");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickOrders() throws IOException {
////            Parent fxml = App.loadFXML("Orders");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickSales() throws IOException {
////            Parent fxml = App.loadFXML("Sales");
////            mainContent.getChildren().setAll(fxml);
////        }
////
////        @FXML
////        private void clickLogout() throws IOException {
////            UserSession.clear();
////            App.setRoot("Login_Page");
////        }
////
////}
