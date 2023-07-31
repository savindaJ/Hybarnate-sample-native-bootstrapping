package lk.ijse.hybernate.sample.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomAlert extends Alert {
    public CustomAlert(AlertType alertType, String title, String header, String message, ButtonType... buttonTypes) {
        super(alertType, message, buttonTypes);
        setTitle(title);
        setHeaderText(header);

        String image = null;
        switch (alertType) {
            case ERROR:
                image = "/asset/";
                break;
            case INFORMATION:
                image = "/asset/";
                break;
            case WARNING:
                image = "/asset/";
                break;
        }
        if (image !=null){
            ImageView imgView = new ImageView(new Image(image));
            imgView.setFitWidth(40);
            imgView.setFitHeight(40);
            setGraphic(imgView);
        }
    }

}
