package lk.ijse.gem_management_layered.controller;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.SalesBO;
import lk.ijse.gem_management_layered.dto.SalesDTO;

import java.util.List;

public class SalesController {

        @FXML
        private TextField saleid;

        @FXML
        private TextField cancel;

        @FXML
        private TextField update;

        @FXML
        private TextField retuurn;

        @FXML
        private TableView<SalesDTO> tablesales;

        @FXML
        private TableColumn<SalesDTO,Integer> colsaleid;

        @FXML
        private TableColumn<SalesDTO,String> colcancel;

        @FXML
        private TableColumn<SalesDTO,String> colupdate;

        @FXML
        private TableColumn<SalesDTO,String> colreturn;

        private final SalesBO salesBO =
                (SalesBO) BOFactory.getInstance().getBO(BOFactory.BOType.SALES);

        public void initialize(){

            colsaleid.setCellValueFactory(new PropertyValueFactory<>("saleId"));
            colcancel.setCellValueFactory(new PropertyValueFactory<>("cancel"));
            colupdate.setCellValueFactory(new PropertyValueFactory<>("updateInfo"));
            colreturn.setCellValueFactory(new PropertyValueFactory<>("returnInfo"));

            loadTable();
        }

        public void saveSale(){

            try{

                SalesDTO dto = new SalesDTO(
                        cancel.getText(),
                        update.getText(),
                        retuurn.getText()
                );

                salesBO.saveSale(dto);

                loadTable();

            }catch(Exception e){
                e.printStackTrace();
            }
        }

        private void loadTable(){

            try{

                List<SalesDTO> list = salesBO.getAllSales();

                tablesales.setItems(
                        FXCollections.observableArrayList(list)
                );

            }catch(Exception e){
                e.printStackTrace();
            }
        }

}
