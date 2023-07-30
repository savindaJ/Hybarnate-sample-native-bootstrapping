package lk.ijse.hybernate.sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private JFXButton btnLogin;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().equals("savinda") && txtPassword.getText().equals("savinda")){

        }
    }
}
