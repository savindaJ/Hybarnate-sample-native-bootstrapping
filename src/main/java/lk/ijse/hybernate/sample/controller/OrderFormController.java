package lk.ijse.hybernate.sample.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OrderFormController {
    public AnchorPane root;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox cmbItemCode;
    public Label lblDescription;
    public Label lblUnitPrice;
    public Label lblQtyOnHand;
    public TextField txtQty;
    public TableView tblOrderCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TableColumn colAction;
    public Label lblNetTotal;

    public void mouseEnterd(MouseEvent event) {
    }

    public void mouseExit(MouseEvent event) {
    }

    public void btnBackOnAction(MouseEvent event) {

    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {

    }
}
