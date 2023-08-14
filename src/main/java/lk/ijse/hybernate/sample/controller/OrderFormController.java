package lk.ijse.hybernate.sample.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hybernate.sample.config.StandardConfig;
import lk.ijse.hybernate.sample.entity.Customer;
import lk.ijse.hybernate.sample.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

    @FXML
    void initialize(){
        setCellValueFactory();
        setCustomerId();
        setItemId();
    }

    private void setItemId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<Item> resultList = getAllItem();

        for (Item item : resultList){
            obList.add(String.valueOf(item.getId()));
        }
        cmbItemCode.setItems(obList);
    }

    private List<Item> getAllItem() {
        try (Session session = StandardConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Item> query = criteriaBuilder.createQuery(Item.class);
            query.from(Item.class);
            List<Item> resultList = session.createQuery(query).getResultList();
            transaction.commit();
            return resultList;
        }

    }

    private void setCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        List<Customer> resultList = getAll();

        for (Customer customer : resultList){
            obList.add(String.valueOf(customer.getId()));
        }
        cmbCustomerId.setItems(obList);
    }

    private List<Customer> getAll() {
        try (Session session = StandardConfig.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);

            query.from(Customer.class);

            List<Customer> resultList = session.createQuery(query).getResultList();

            transaction.commit();

            return resultList;
        }
    }

    void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
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

    public void mouseExit(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

    public void btnBackOnAction(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/home.fxml")));
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
