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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hybernate.sample.config.StandardConfig;
import lk.ijse.hybernate.sample.entity.Item;
import lk.ijse.hybernate.sample.util.CustomAlert;
import lk.ijse.hybernate.sample.util.tm.ItemTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ItemController {
    public JFXTextField txtName;
    public JFXTextField txtQty;
    public JFXTextField txtItemPrice;
    public JFXTextField txtCode;
    public JFXComboBox<String> cmbId;
    public TableView tblItem;

    public AnchorPane root;
    public TableColumn colCode;
    public TableColumn colItemName;
    public TableColumn colQty;
    public TableColumn colPrice;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    @FXML
    void initialize(){
        setItemID();
        fillTable();
        setCellValueFactory();
        initUI();
    }

    private void initUI(){
        txtItemPrice.setDisable(true);
        txtName.setDisable(true);
        txtQty.setDisable(true);
        txtCode.setDisable(true);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(true);
    }
    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private List<Item> getAllItem(){
        try (Session session = StandardConfig.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Item> query = builder.createQuery(Item.class);

            query.from(Item.class);

            List<Item> resultList = session.createQuery(query).getResultList();

            transaction.commit();

            return resultList;
        }
    }

    private void fillTable() {
        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();
        for (Item item : getAllItem()) {
            itemTMS.add(new ItemTM(item.getId(), item.getItemName(), item.getUnitPrice(), item.getQty()));
        }
        tblItem.setItems(itemTMS);
    }

    private void setItemID() {
        ObservableList <String> idList = FXCollections.observableArrayList();
        for (Item item : getAllItem()) {
            idList.add(String.valueOf(item.getId()));
        }
        cmbId.setItems(idList);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try (Session session = StandardConfig.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            lk.ijse.hybernate.sample.entity.Item itemCopy = new lk.ijse.hybernate.sample.entity.Item(Integer.parseInt(txtCode.getText()),txtName.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtItemPrice.getText()));

            Serializable save = session.save(itemCopy);
            transaction.commit();

            if (!(save ==null))
                new CustomAlert(Alert.AlertType.CONFIRMATION,"confirmation","saved !","Customer Saved !").show();
            else
                new CustomAlert(Alert.AlertType.ERROR,"Error !","Not Saved !","Customer not Saved Try again !").show();
        }
        fillTable();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try (Session session = StandardConfig.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            Item item = session.get(Item.class, Integer.parseInt(cmbId.getValue()));

            item.setUnitPrice(Double.valueOf(txtItemPrice.getText()));
            item.setQty(Integer.valueOf(txtQty.getText()));
            item.setId(Integer.parseInt(txtCode.getText()));
            item.setItemName(txtName.getText());

            Serializable save = session.save(item);

            transaction.commit();

            if (!(save == null))
                new CustomAlert(Alert.AlertType.INFORMATION,"Update","Updated !","Item Updated !").show();
            else
                new CustomAlert(Alert.AlertType.ERROR,"Update","Not Updated !","Item Not Completed Updated !").show();
        }
            fillTable();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try (Session session = StandardConfig.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            Item item = session.get(Item.class, Integer.parseInt(cmbId.getValue()));

            session.delete(item);

            transaction.commit();

            new CustomAlert(Alert.AlertType.CONFIRMATION,"Delete ","Deleted !","Item Completed Deleted !").show();

        }catch (Exception e){
            new CustomAlert(Alert.AlertType.ERROR,"Delete ","Not Deleted !",e.getMessage()).show();
        }
        fillTable();
    }

    public void cmbIdOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        txtItemPrice.setDisable(false);
        txtName.setDisable(false);
        txtQty.setDisable(false);
        txtCode.setDisable(false);

        try (Session session = StandardConfig.getInstance().getSession()) {

            Transaction transaction = session.beginTransaction();

            Item item = session.get(Item.class, Integer.parseInt(cmbId.getValue()));

            transaction.commit();

            txtCode.setText(String.valueOf(item.getId()));
            txtName.setText(item.getItemName());
            txtQty.setText(String.valueOf(item.getQty()));
            txtItemPrice.setText(String.valueOf(item.getUnitPrice()));
        }
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
        btnSave.setDisable(false);
        txtItemPrice.setDisable(false);
        txtName.setDisable(false);
        txtQty.setDisable(false);
        txtCode.setDisable(false);
        txtName.requestFocus();

    }
}
