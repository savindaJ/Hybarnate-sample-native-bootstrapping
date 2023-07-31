package lk.ijse.hybernate.sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hybernate.sample.config.SessionFactoryConfigToProperty;
import lk.ijse.hybernate.sample.entity.Customer;
import lk.ijse.hybernate.sample.util.CustomAlert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class CustomerController {
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXTextField txtId;
    public JFXComboBox cmbId;
    public TableView tblCustomer;

    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    @FXML
    void initialize(){
        initUi();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = new Customer(txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()),txtId.getText());
            Serializable save = session.save(customer);
            transaction.commit();

            if (!(save ==null)){
                initUi();
                new CustomAlert(Alert.AlertType.CONFIRMATION,"confirmation","saved !","Customer Saved !").show();
            }else {
                new CustomAlert(Alert.AlertType.ERROR,"Error !","Not Saved !","Customer not Saved Try again !").show();
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();
//            session.get(Customer.class,);
        }

        new CustomAlert(Alert.AlertType.INFORMATION,"Update","Updated !","Customer Updated !").show();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

//            session.delete();
        }
    }

    public void cmbIdOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void initUi(){
        txtAddress.clear();
        txtId.clear();
        txtId.clear();
        txtSalary.clear();
        txtAddress.setDisable(true);
        txtId.setDisable(true);
        txtId.setDisable(true);
        txtSalary.setDisable(true);
        btnSave.setDisable(true);
        txtName.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }

    public void btnBackClicked(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/menu.fxml")));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();

            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();

        }
    }

    public void mouseEnterd(MouseEvent event) {
        if (event.getSource() instanceof javafx.scene.image.ImageView) {
            javafx.scene.image.ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
//            glow.setColor(Color.valueOf("#EF233C"));
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(15);
            glow.setHeight(15);
            glow.setRadius(15);
            icon.setEffect(glow);
        }

    }

    public void mouseExsits(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

    public void btnAddNewOnAction(ActionEvent actionEvent) {
        txtAddress.setDisable(false);
        txtId.setDisable(false);
        txtId.setDisable(false);
        txtSalary.setDisable(false);
        btnSave.setDisable(false);
        txtName.setDisable(false);
        btnSave.requestFocus();
    }
}
