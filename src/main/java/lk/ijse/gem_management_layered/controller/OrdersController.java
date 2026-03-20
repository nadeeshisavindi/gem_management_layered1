package lk.ijse.gem_management_layered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gem_management_layered.bo.BOFactory;
import lk.ijse.gem_management_layered.bo.custom.CustomerBO;
import lk.ijse.gem_management_layered.bo.custom.GemBO;
import lk.ijse.gem_management_layered.bo.custom.Gem_CutterBO;
import lk.ijse.gem_management_layered.bo.custom.OrdersBO;
import lk.ijse.gem_management_layered.bo.custom.impl.OrdersBOImpl;
import lk.ijse.gem_management_layered.db.DBConnection;
import lk.ijse.gem_management_layered.dto.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
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
    private final CustomerBO customerBO =
            (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    private final Gem_CutterBO gemCutterBO =
            (Gem_CutterBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM_CUTTER);
    private final GemBO gemBO =
            (GemBO) BOFactory.getInstance().getBO(BOFactory.BOType.GEM);

    private final ObservableList<OrderDetailDTO> orderDetailsList = FXCollections.observableArrayList();

    private final Map<String, Integer> customerMap = new HashMap<>();
    private final Map<String, Integer> gemCutterMap = new HashMap<>();
    private final Map<String, Integer> gemMap = new HashMap<>();

    @FXML
    public void initialize() {
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



    private void loadCustomers() {
        try {
            customerMap.clear();
            List<CustomerDTO> list = customerBO.getAllCustomers();
            for (CustomerDTO c : list) {
                String name = c.getFirstName() + " " + c.getLastName();
                customerMap.put(name, c.getId());
            }
            cmbCustomer.setItems(FXCollections.observableArrayList(customerMap.keySet()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGemCutters() {
        try {
            gemCutterMap.clear();
            List<Gem_CutterDTO> list = gemCutterBO.getAllCutters();
            for (Gem_CutterDTO gc : list) {
                gemCutterMap.put(gc.getCutterName(), gc.getCutterId());
            }
            cmbGemCutter.setItems(FXCollections.observableArrayList(gemCutterMap.keySet()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGems() {
        try {
            gemMap.clear();
            List<GemDTO> list = gemBO.getAllGems();
            for (GemDTO g : list) {
                gemMap.put(g.getGemName(), g.getGemId());
            }
            cmbGem.setItems(FXCollections.observableArrayList(gemMap.keySet()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if (order != null && order.getOrderDetails() != null) {
                orderDetailsList.addAll(order.getOrderDetails());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void addOrderDetail(ActionEvent event) {
        String gemName = cmbGem.getValue();
        if (gemName == null) { alert(Alert.AlertType.ERROR, "Select a gem"); return; }
        if (!quantityField.getText().matches("\\d+")) { alert(Alert.AlertType.ERROR, "Enter valid quantity"); return; }
        if (!unitPriceField.getText().matches("\\d+(\\.\\d+)?")) { alert(Alert.AlertType.ERROR, "Enter valid unit price"); return; }

        int gemId = gemMap.get(gemName);
        orderDetailsList.add(new OrderDetailDTO(
                0, 0, gemId, gemName,
                Integer.parseInt(quantityField.getText()),
                Double.parseDouble(unitPriceField.getText())
        ));
        cmbGem.getSelectionModel().clearSelection();
        quantityField.clear();
        unitPriceField.clear();
    }

    @FXML
    private void removeOrderDetail(ActionEvent event) {
        OrderDetailDTO selected = tableOrderDetails.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert(Alert.AlertType.ERROR, "Select a detail row to remove");
            return;
        }
        orderDetailsList.remove(selected);
    }


    @FXML
    private void saveOrder(ActionEvent event) {
        if (!isValidOrder()) return;
        try {
            OrdersDTO order = new OrdersDTO(
                    customerMap.get(cmbCustomer.getValue()),
                    gemCutterMap.get(cmbGemCutter.getValue()),
                    orderDatePicker.getValue(),
                    new ArrayList<>(orderDetailsList)
            );
            ordersBO.saveOrder(order);
            alert(Alert.AlertType.INFORMATION, "Order Saved Successfully!");
            clearAll();
            loadAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "Save Failed: " + e.getMessage());
        }
    }

    @FXML
    private void updateOrder(ActionEvent event) {
        if (orderIdField.getText().isEmpty() || !orderIdField.getText().matches("\\d+")) {
            alert(Alert.AlertType.ERROR, "Select an order to update");
            return;
        }
        if (!isValidOrder()) return;
        try {
            OrdersDTO order = new OrdersDTO(
                    Integer.parseInt(orderIdField.getText()),
                    customerMap.get(cmbCustomer.getValue()),
                    gemCutterMap.get(cmbGemCutter.getValue()),
                    orderDatePicker.getValue(),
                    new ArrayList<>(orderDetailsList)
            );
            ordersBO.updateOrder(order);
            alert(Alert.AlertType.INFORMATION, "Order Updated Successfully!");
            clearAll();
            loadAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "Update Failed: " + e.getMessage());
        }
    }

    @FXML
    private void deleteOrder(ActionEvent event) {
        if (orderIdField.getText().isEmpty() || !orderIdField.getText().matches("\\d+")) {
            alert(Alert.AlertType.ERROR, "Select an order to delete");
            return;
        }
        try {
            ordersBO.deleteOrder(Integer.parseInt(orderIdField.getText()));
            alert(Alert.AlertType.INFORMATION, "Order Deleted Successfully!");
            clearAll();
            loadAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
            alert(Alert.AlertType.ERROR, "Delete Failed: " + e.getMessage());
        }
    }

    @FXML
    private void resetOrder(ActionEvent event) {
        clearAll();
    }

    @FXML
    public void generateReport(ActionEvent event) {
        try {
            // Load the report template
            InputStream reportStream = getClass().getResourceAsStream("/lk/ijse/gem_management_layered/reports/GemReport.jrxml");

            // Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Get DB connection
            Connection connection = DBConnection.getInstance().getConnection();

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);

            // Show the report in a viewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {

            alert(Alert.AlertType.ERROR, "Report Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidOrder() {
        if (cmbCustomer.getValue() == null) {
            alert(Alert.AlertType.ERROR, "Select a customer"); return false;
        }
        if (cmbGemCutter.getValue() == null) {
            alert(Alert.AlertType.ERROR, "Select a gem cutter"); return false;
        }
        if (orderDatePicker.getValue() == null) {
            alert(Alert.AlertType.ERROR, "Select an order date"); return false;
        }
        if (orderDetailsList.isEmpty()) {
            alert(Alert.AlertType.ERROR, "Add at least one order detail"); return false;
        }
        return true;
    }

    private void clearAll() {
        orderIdField.clear();
        cmbCustomer.getSelectionModel().clearSelection();
        cmbGemCutter.getSelectionModel().clearSelection();
        cmbGem.getSelectionModel().clearSelection();
        orderDatePicker.setValue(LocalDate.now());
        orderDetailsList.clear();
    }

    private void alert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}