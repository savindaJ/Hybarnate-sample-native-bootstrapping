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
import javafx.scene.control.*;
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
import lk.ijse.hybernate.sample.util.CartTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderFormController {
    public AnchorPane root;
    public Label lblOrderId;
    public Label lblOrderDate;
    public JFXComboBox<String> cmbCustomerId;
    public Label lblCustomerName;
    public JFXComboBox<String> cmbItemCode;
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

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();

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
        if (Double.parseDouble(txtQty.getText())<=Double.parseDouble(lblQtyOnHand.getText())){
            lblQtyOnHand.setText(String.valueOf(Double.parseDouble(lblQtyOnHand.getText())-Double.parseDouble(txtQty.getText())));
            String code = cmbItemCode.getValue();
            String description = lblDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(lblUnitPrice.getText());
            double total = qty * unitPrice;

            Button btn = new Button("Remove");
            setRemoveBtnOnAction(btn); /* set action to the btnRemove */

            if (!obList.isEmpty()) {
                for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                    if (colItemCode.getCellData(i).equals(code)) {
                        qty += (int) colQty.getCellData(i);
                        total = qty * unitPrice;
                        System.out.println(total);
                        obList.get(i).setQty(qty);
                        obList.get(i).setTotal(total);

                        tblOrderCart.refresh();
                        calculateNetTotal();
                        return;
                    }
                }
            }

            CartTM tm = new CartTM(code, description, qty, unitPrice, total, btn);

            obList.add(tm);
            tblOrderCart.setItems(obList);

            calculateNetTotal();

            txtQty.setText("");
        }else {
            new Alert(Alert.AlertType.ERROR,"unable to qty !, enter low qty please !").show();
        }

    }

    private void calculateNetTotal() {
        System.out.println(tblOrderCart.getItems().size());
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                try {
                    int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                    System.out.println(selectedIndex);
                    obList.remove(selectedIndex);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                }catch (Exception exception){
                    new Alert(Alert.AlertType.ERROR,"please select delete row... !").show();
                }
            }

        });
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        try (Session session = StandardConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Item item = session.get(Item.class, Integer.parseInt(cmbItemCode.getValue()));
            transaction.commit();
            lblDescription.setText(item.getItemName());
            lblQtyOnHand.setText(String.valueOf(item.getQty()));
            lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        }
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
        try (Session session = StandardConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, Integer.parseInt(cmbCustomerId.getValue()));
            transaction.commit();
            lblCustomerName.setText(customer.getName());
        }
    }
}
