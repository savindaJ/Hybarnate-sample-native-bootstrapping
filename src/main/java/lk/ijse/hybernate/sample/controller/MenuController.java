package lk.ijse.hybernate.sample.controller;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MenuController {
    public Label lblTitle;
    public ImageView imgOrder;
    public ImageView imgItem;
    public ImageView imgCustomer;

    public void btnCustomerClick(MouseEvent mouseEvent) {

    }

    public void btnItemClick(MouseEvent mouseEvent) {
    }

    public void btnOrderClick(MouseEvent mouseEvent) {
    }

    public void mouseenterd(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView view = (ImageView) event.getSource();

            switch (view.getId()){
                case "imgOrder":
                    lblTitle.setText("Goto Order Form");
                    break;
                case "imgCustomer":
                    lblTitle.setText("Goto Customer Form");
                    break;
                case "imgItem" :
                    lblTitle.setText("Goto Item Form");
                    break;
                default:
                    break;
            }
        }
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

    public void mouseexsits(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }
}
