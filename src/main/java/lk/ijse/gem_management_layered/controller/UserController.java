package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.gem_management_layered.bo.custom.UserBO;
import lk.ijse.gem_management_layered.bo.custom.impl.UserBOImpl;
import lk.ijse.gem_management_layered.dto.UserDTO;

import java.util.List;

public class UserController {

    @FXML
    private TextField userid, username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> role;
    @FXML
    private TableView<UserDTO> tableuser;
    @FXML
    private TableColumn<UserDTO, Integer> tableuserid;
    @FXML
    private TableColumn<UserDTO, String> tableusername;
    @FXML
    private TableColumn<UserDTO, String> tablerole;

    private final UserBO userBO = new UserBOImpl();

    @FXML
    public void initialize() {
        role.getItems().addAll("OWNER", "ADMIN", "CASHIER");

        tableuserid.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getUserId()).asObject());
        tableusername.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getUsername()));
        tablerole.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getRole()));

        loadUserTable();
    }

    @FXML
    private void userSave(ActionEvent event) {
        try {
            UserDTO dto = new UserDTO(username.getText(), password.getText(), role.getValue());
            if (userBO.saveUser(dto)) loadUserTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadUserTable() {
        try {
            List<UserDTO> list = userBO.getAllUsers();
            tableuser.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
//}
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.Parent;
//import lk.ijse.gem_management_layered.App;
//import lk.ijse.gem_management_layered.dto.UserDTO;
//import lk.ijse.gem_management_layered.util.UserSession;
//
//import java.io.IOException;
//
//    public class UserController {
//
//        @FXML private AnchorPane mainContent;
//
//        @FXML private Button btnUsers;
//        @FXML private Button btnCustomers;
//        @FXML private Button btnGem;
//        @FXML private Button btnStock;
//        @FXML private Button btnSuppliers;
//        @FXML private Button btnGemCutter;
//        @FXML private Button btnOrders;
//        @FXML private Button btnSales;
//        @FXML private Button btnLogout;
//
//        @FXML
//        public void initialize() {
//            applyRolePermissions();
//        }
//
//        private void applyRolePermissions() {
//            UserDTO user = UserSession.getUser();
//            if(user == null) return;
//
//            String role = user.getRole().toUpperCase();
//
//            switch(role){
//                case "OWNER":
//                    // Full access → do nothing
//                    break;
//                case "ADMIN":
//                    btnUsers.setDisable(true); // Admin cannot manage users
//                    break;
//                case "CASHIER":
//                    btnUsers.setDisable(true);
//                    btnSuppliers.setDisable(true);
//                    btnGem.setDisable(true);
//                    btnStock.setDisable(true);
//                    btnGemCutter.setDisable(true);
//                    btnOrders.setDisable(true);
//                    break;
//                default:
//                    btnUsers.setDisable(true);
//                    btnCustomers.setDisable(true);
//                    btnGem.setDisable(true);
//                    btnStock.setDisable(true);
//                    btnSuppliers.setDisable(true);
//                    btnGemCutter.setDisable(true);
//                    btnOrders.setDisable(true);
//                    btnSales.setDisable(true);
//            }
//        }
//
//        // ================= Navigation =================
//        @FXML
//        private void clickUsers() throws IOException {
//            Parent fxml = App.loadFXML("User");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickCustomers() throws IOException {
//            Parent fxml = App.loadFXML("Customers");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickGem() throws IOException {
//            Parent fxml = App.loadFXML("Gem");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickStock() throws IOException {
//            Parent fxml = App.loadFXML("Stock");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickSuppliers() throws IOException {
//            Parent fxml = App.loadFXML("Suppliers");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickGemCutter() throws IOException {
//            Parent fxml = App.loadFXML("Gem_Cutter");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickOrders() throws IOException {
//            Parent fxml = App.loadFXML("Orders");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickSales() throws IOException {
//            Parent fxml = App.loadFXML("Sales");
//            mainContent.getChildren().setAll(fxml);
//        }
//
//        @FXML
//        private void clickLogout() throws IOException {
//            UserSession.clear();
//            App.setRoot("Login_Page");
//        }
//
//}
