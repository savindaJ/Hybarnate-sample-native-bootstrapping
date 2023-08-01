package lk.ijse.hybernate.sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CustomerController {
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXTextField txtId;
    public JFXComboBox<String> cmbId;
    public TableView tblCustomer;

    public AnchorPane root;
    public JFXButton btnAddNew;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    @FXML
    void initialize(){
        initUi();
        setCustomerID();
        setTbData();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colGmail.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private void setTbData() {

    }

    private void setCustomerID() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<Customer> resultList = getAll();

            for (Customer customer : resultList){
                obList.add(customer.getId());
            }
            cmbId.setItems(obList);
        }

    private List<Customer> getAll() {

        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);

            query.from(Customer.class);

            List<Customer> resultList = session.createQuery(query).getResultList();

            transaction.commit();

            return resultList;
    }

}

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = null;
            Serializable save=null;
            try {
                customer = new Customer(txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()),txtId.getText());
                save = session.save(customer);
                transaction.commit();
            }catch (Exception e){
//                new CustomAlert(Alert.AlertType.ERROR,"Error !","Empty !","Data is Empty Try again !").show();
            }

            if (!(save ==null)){
                initUi();
                new CustomAlert(Alert.AlertType.CONFIRMATION,"confirmation","saved !","Customer Saved !").show();
            }else {
                new CustomAlert(Alert.AlertType.ERROR,"Error !","Not Saved !","Customer not Saved Try again !").show();
            }
        }
        setCustomerID();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, cmbId.getValue());
            customer.setName(txtName.getText());
            customer.setId(txtId.getText());
            customer.setAddress(txtAddress.getText());
            customer.setSalary(Double.valueOf(txtSalary.getText()));
            Serializable save = session.save(customer);
            transaction.commit();

            if (!(save ==null))
                new CustomAlert(Alert.AlertType.INFORMATION,"Update","Updated !","Customer Updated !").show();
            else
                new CustomAlert(Alert.AlertType.ERROR,"Update","Not Updated !","Customer Not Completed Updated !").show();
        }
        initUi();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, cmbId.getValue());
            session.delete(customer);
            transaction.commit();
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete ","Deleted !","Customer Completed Deleted !").show();
            setCustomerID();
            cmbId.setValue("");
        }catch (Exception e){
            new CustomAlert(Alert.AlertType.ERROR,"Delete ","Deleted !",e.getMessage()).show();
        }
        initUi();
    }

    public void cmbIdOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);

        try (Session session = SessionFactoryConfigToProperty.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, cmbId.getValue());
            transaction.commit();

            txtName.setText(customer.getName());
            txtSalary.setText(String.valueOf(customer.getSalary()));
            txtId.setText(customer.getId());
            txtAddress.setText(customer.getAddress());
        }
        txtAddress.setDisable(false);
        txtId.setDisable(false);
        txtId.setDisable(false);
        txtSalary.setDisable(false);
        btnSave.setDisable(false);
        txtName.setDisable(false);
        txtName.requestFocus();
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
        txtName.requestFocus();
    }
}
