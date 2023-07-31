package lk.ijse.hybernate.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
       /* Customer customer = new Customer();
        customer.setAddress("matara");
        customer.setContact(200.78);
        customer.setName("kamal");
        customer.setId("S001");
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();*/

        /*Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer s001 = session.get(Customer.class, "S001");
        s001.setName("SAVINDA");
        s001.setAddress("Dickwalla");
        session.save(s001);
        transaction.commit();
        session.close();*/


        /*Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer s001 = session.get(Customer.class, "S001");
        session.delete(s001);
        transaction.commit();
        session.close();*/

        /*Session session = SessionFactoryConfigToProperty.getInstance().getSession();
        Transaction transaction = session.beginTransaction();*/

       /* Item item = new Item();
        item.setItemCode("I002");
        item.setName("Cake");
        item.setPrice(100.36);
        item.setQty(10);

        session.save(item);*/

        /*Item item = session.get(Item.class, "I001");
        item.setQty(30);
        item.setPrice(8000.0);
        session.update(item);
        Customer s002 = session.get(Customer.class, "S002");
        session.delete(s002);
        transaction.commit();
        session.close();*/
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/menu.fxml"))));
        primaryStage.setTitle("hibernate");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
