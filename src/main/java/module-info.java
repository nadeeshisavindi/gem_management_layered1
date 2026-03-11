module lk.ijse.gem_management_layered {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens lk.ijse.gem_management_layered to javafx.fxml;
    exports lk.ijse.gem_management_layered.controller;
    opens lk.ijse.gem_management_layered.controller to javafx.fxml;
    exports lk.ijse.gem_management_layered;
    exports lk.ijse.gem_management_layered.bo;
    opens lk.ijse.gem_management_layered.bo to javafx.fxml;
    exports lk.ijse.gem_management_layered.bo.custom;
    opens lk.ijse.gem_management_layered.bo.custom to javafx.fxml;
    exports lk.ijse.gem_management_layered.bo.custom.impl;
    opens lk.ijse.gem_management_layered.bo.custom.impl to javafx.fxml;



}