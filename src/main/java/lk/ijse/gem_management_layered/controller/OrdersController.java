package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gem_management_layered.bo.custom.OrdersBO;
import lk.ijse.gem_management_layered.bo.custom.impl.OrdersBOImpl;
import lk.ijse.gem_management_layered.dto.OrderDetailDTO;
import lk.ijse.gem_management_layered.dto.OrdersDTO;
import lk.ijse.gem_management_layered.dto.OrdersTableDTO;

import java.time.LocalDate;
import java.util.*;

public class OrdersController {

    @FXML private TextField orderIdField;
    @FXML private ComboBox<String> cmbCustomer;
    @FXML private ComboBox<String> cmbGemCutter;
    @FXML private ComboBox<String> cmbGem;
    @FXML private DatePicker orderDatePicker;

    @FXML private TableView<OrdersTableDTO> tableOrders;
    @FXML private TableColumn<OrdersTableDTO, Integer> colOrderId;
    @FXML private TableColumn<OrdersTableDTO, String> colCustomerName;
    @FXML private TableColumn<OrdersTableDTO, String> colGemCutterName;
    @FXML private TableColumn<OrdersTableDTO, LocalDate> colOrderDate;

    @FXML private TableView<OrderDetailDTO> tableOrderDetails;
    @FXML private TableColumn<OrderDetailDTO, String> colGemName;
    @FXML private TableColumn<OrderDetailDTO, Integer> colQuantity;
    @FXML private TableColumn<OrderDetailDTO, Double> colUnitPrice;

    @FXML private TextField quantityField;
    @FXML private TextField unitPriceField;

    private final OrdersBO ordersBO = new OrdersBOImpl();
    private final ObservableList<OrderDetailDTO> orderDetailsList = FXCollections.observableArrayList();

    private final Map<String, Integer> customerMap = new HashMap<>();
    private final Map<String, Integer> gemCutterMap = new HashMap<>();
    private final Map<String, Integer> gemMap = new HashMap<>();

    @FXML
    public void initialize() {
        // Table columns
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colGemCutterName.setCellValueFactory(new PropertyValueFactory<>("gemCutterName"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        colGemName.setCellValueFactory(new PropertyValueFactory<>("gemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        tableOrderDetails.setItems(orderDetailsList);

        loadCustomers();
        loadGemCutters();
        loadGems();
        loadAllOrders();
        setOrderTableListener();
        orderDatePicker.setValue(LocalDate.now());
    }

    private void loadGemCutters() {
        // TODO: implement loading gem cutters into cmbGemCutter and gemCutterMap
    }

    private void loadCustomers() {

    }

    private void loadGems() {

    }

    private void loadAllOrders() {
        try {
            tableOrders.setItems(FXCollections.observableArrayList(ordersBO.getAllOrders()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOrderTableListener() {
        tableOrders.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, selected) -> {
                    if (selected != null) {
                        orderIdField.setText(String.valueOf(selected.getOrderId()));
                        cmbCustomer.setValue(selected.getCustomerName());
                        cmbGemCutter.setValue(selected.getGemCutterName());
                        orderDatePicker.setValue(selected.getOrderDate());
                        loadOrderDetails(selected.getOrderId());
                    }
                }
        );
    }

    private void loadOrderDetails(int orderId) {
        try {
            OrdersDTO order = ordersBO.searchOrder(orderId);
            orderDetailsList.clear();
            orderDetailsList.addAll(order.getOrderDetails());
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    private void addOrderDetail(ActionEvent event) {
        String gemName = cmbGem.getValue();
        if (gemName == null || !quantityField.getText().matches("\\d+") || !unitPriceField.getText().matches("\\d+(\\.\\d+)?")) {
            alert("Invalid Input");
            return;
        }

        int gemId = gemMap.get(gemName);
        orderDetailsList.add(new OrderDetailDTO(0, 0, gemId, gemName,
                Integer.parseInt(quantityField.getText()),
                Double.parseDouble(unitPriceField.getText())));
        cmbGem.getSelectionModel().clearSelection();
        quantityField.clear();
        unitPriceField.clear();
    }

    @FXML
    private void saveOrder(ActionEvent event) {
        try {
            OrdersDTO order = new OrdersDTO(
                    customerMap.get(cmbCustomer.getValue()),
                    gemCutterMap.get(cmbGemCutter.getValue()),
                    orderDatePicker.getValue(),
                    new ArrayList<>(orderDetailsList)
            );
            ordersBO.saveOrder(order);
            alert("Order Saved Successfully");
            clearAll();
            loadAllOrders();
        } catch (Exception e) { e.printStackTrace(); alert("Save Failed"); }
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).show();
    }

    private void clearAll() {
        orderIdField.clear();
        cmbCustomer.getSelectionModel().clearSelection();
        cmbGemCutter.getSelectionModel().clearSelection();
        cmbGem.getSelectionModel().clearSelection();
        orderDatePicker.setValue(LocalDate.now());
        orderDetailsList.clear();
    }

    // TODO: Implement loadCustomers(), loadGemCutters(), loadGems() similar to previous controller
}