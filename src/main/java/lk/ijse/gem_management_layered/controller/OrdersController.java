package lk.ijse.gem_management_layered.controller;



import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.OrdersBO;
import lk.ijse.gem_management_layered.dto.OrdersDTO;

import java.time.LocalDate;
import java.util.List;

public class OrdersController {

    @FXML
    private TextField orderId;

    @FXML
    private TextField customerId;

    @FXML
    private TextField gemCutterId;

    @FXML
    private DatePicker orderDate;

    @FXML
    private TableView<OrdersDTO> tableOrders;

    @FXML
    private TableColumn<OrdersDTO,Integer> colOrderId;

    @FXML
    private TableColumn<OrdersDTO,Integer> colCustomerId;

    @FXML
    private TableColumn<OrdersDTO,Integer> colGemCutterId;

    @FXML
    private TableColumn<OrdersDTO, LocalDate> colOrderDate;

    private final OrdersBO ordersBO =
            (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOType.ORDERS);

    @FXML
    public void initialize(){

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colGemCutterId.setCellValueFactory(new PropertyValueFactory<>("gemCutterId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        loadOrders();
    }

    // SAVE
    @FXML
    public void saveOrder(){

        try{

            OrdersDTO dto = new OrdersDTO(
                    Integer.parseInt(customerId.getText()),
                    Integer.parseInt(gemCutterId.getText()),
                    orderDate.getValue(),
                    null
            );

            boolean isSaved = ordersBO.saveOrder(dto);

            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Order Saved").show();
                clearFields();
                loadOrders();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // UPDATE
    @FXML
    public void updateOrder(){

        try{

            OrdersDTO dto = new OrdersDTO(
                    Integer.parseInt(orderId.getText()),
                    Integer.parseInt(customerId.getText()),
                    Integer.parseInt(gemCutterId.getText()),
                    orderDate.getValue(),
                    null
            );

            boolean isUpdated = ordersBO.updateOrder(dto);

            if(isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Order Updated").show();
                clearFields();
                loadOrders();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // DELETE
    @FXML
    public void deleteOrder(){

        try{

            boolean isDeleted = ordersBO.deleteOrder(
                    Integer.parseInt(orderId.getText())
            );

            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Order Deleted").show();
                clearFields();
                loadOrders();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // SEARCH
    @FXML
    public void searchOrder(){

        try{

            OrdersDTO dto = ordersBO.searchOrder(
                    Integer.parseInt(orderId.getText())
            );

            if(dto != null){

                customerId.setText(String.valueOf(dto.getCustomerId()));
                gemCutterId.setText(String.valueOf(dto.getGemCutterId()));
                orderDate.setValue(dto.getOrderDate());

            }else{
                new Alert(Alert.AlertType.INFORMATION,"Order Not Found").show();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void loadOrders(){

        try{

            List<OrdersDTO> list = ordersBO.getAllOrders();

            tableOrders.setItems(
                    FXCollections.observableArrayList(list)
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void clearFields(){

        orderId.clear();
        customerId.clear();
        gemCutterId.clear();
        orderDate.setValue(null);
    }

}
