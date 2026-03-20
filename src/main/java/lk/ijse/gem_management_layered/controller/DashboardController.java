package lk.ijse.gem_management_layered.controller;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import lk.ijse.gem_management_layered.App;
import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.util.CRUDUtill;
//import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.util.UserSession;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

    public class DashboardController implements Initializable {

        @FXML
        private AnchorPane maincontent;

        @FXML private Button btnUsers;
        @FXML private Button btnCustomers;
        @FXML private Button btnGem;
        @FXML private Button btnStock;
        @FXML private Button btnSuppliers;
        @FXML private Button btnGemCutter;
        @FXML private Button btnOrders;
        @FXML private Button btnSales;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            applyRolePermissions();
        }

        private void applyRolePermissions() {

            UserDTO user = UserSession.getUser();

            if (user == null) return;

            String role = user.getRole().toUpperCase();

            switch (role) {

                case "OWNER":
                    break;

                case "ADMIN":
                    btnUsers.setDisable(true);
                    break;

                case "CASHIER":
                    btnUsers.setDisable(true);
                    btnSuppliers.setDisable(true);
                    break;

                default:
                    btnUsers.setDisable(true);
                    btnCustomers.setDisable(true);
                    btnGem.setDisable(true);
                    btnStock.setDisable(true);
                    btnSuppliers.setDisable(true);
                    btnGemCutter.setDisable(true);
                    btnOrders.setDisable(true);
                    btnSales.setDisable(true);
            }
        }

        @FXML
        private void clickUserNav() throws IOException {
            Parent fxml = App.loadFXML("Users");
            maincontent.getChildren().setAll(fxml);
        }

        @FXML
        private void clickStockNav() throws IOException {
            Parent fxml = App.loadFXML("Stock");
            maincontent.getChildren().setAll(fxml);
        }
        @FXML
        private void clickSupplierNav() throws IOException {
            Parent fxml = App.loadFXML("Suppliers");
            maincontent.getChildren().setAll(fxml);
        }

        @FXML
        private void clickGemCutterNav() throws IOException {
            Parent fxml = App.loadFXML("Gem_Cutter");
            maincontent.getChildren().setAll(fxml);
        }


        @FXML
        private void clickCustomerNav() throws IOException {
            Parent fxml = App.loadFXML("Customers");
            maincontent.getChildren().setAll(fxml);
        }


        @FXML
        private void clickOrderNav() throws IOException {
            Parent fxml = App.loadFXML("Orders");
            maincontent.getChildren().setAll(fxml);
        }

        @FXML
        private void clickSaleNav() throws IOException {
            Parent fxml = App.loadFXML("Sales");
            maincontent.getChildren().setAll(fxml);
        }


        @FXML
        private void clickGemNav() throws IOException {
            Parent fxml = App.loadFXML("Gem");
            maincontent.getChildren().setAll(fxml);
        }

        @FXML
        private void clickLogoutNav() throws IOException {

            UserSession.clear();

            App.setRoot("Login_Page");
        }

}
