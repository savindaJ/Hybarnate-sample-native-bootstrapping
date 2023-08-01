package lk.ijse.hybernate.sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomeController {
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private JFXButton btnLogin;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("") && txtPassword.getText().equals("")){
            URL resource = this.getClass().getResource("/view/menu.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) (this.btnLogin.getScene().getWindow());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            Platform.runLater(() -> primaryStage.sizeToScene());
        }else {
            new Alert(Alert.AlertType.ERROR,"wrong password or userName !").show();
        }
    }
}
