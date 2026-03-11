package lk.ijse.gem_management_layered.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
//import lk.ijse.gem_management_layered.App;
//import lk.ijse.gem_management_layered.bo.custom.UserBO;
//import lk.ijse.gem_management_layered.bo.custom.impl.UserBOImpl;
//import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.App;
import lk.ijse.gem_management_layered.bo.custom.UserBO;
import lk.ijse.gem_management_layered.bo.custom.impl.UserBOImpl;
import lk.ijse.gem_management_layered.dto.UserDTO;
import lk.ijse.gem_management_layered.util.UserSession;

  public class Login_PageController {

        @FXML
        private TextField usernameField;

        @FXML
        private TextField passwordField;

        private final UserBO userBO = new UserBOImpl();

        @FXML
        private void login_page(ActionEvent event) {

            String username = usernameField.getText();
            String password = passwordField.getText();

            try {

                UserDTO user = userBO.checkLogin(username, password);

                if (user != null) {

                    UserSession.setUser(user);

                    App.setRoot("Dashboard");

                } else {

                    showError("Invalid username or password");

                }

            } catch (Exception e) {

                e.printStackTrace();
                showError("System Error!");

            }
        }

        private void showError(String msg) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(msg);
            alert.show();
        }

}
