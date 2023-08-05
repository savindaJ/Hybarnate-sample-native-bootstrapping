package lk.ijse.hybernate.sample.config;

import lk.ijse.hybernate.sample.entity.Customer;
import lk.ijse.hybernate.sample.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfigToProperty {
    private static SessionFactory factory;
    private static SessionFactoryConfigToProperty configToProperty;

    static {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configuration.addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class);
        factory=configuration.setProperties(properties).buildSessionFactory();
    }

    private SessionFactoryConfigToProperty(){
    }

    public static SessionFactoryConfigToProperty getInstance(){
        return configToProperty == null ? new SessionFactoryConfigToProperty() : configToProperty;
    }

    public Session getSession(){
       /* Properties properties = null;
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(Customer.class
                        .getResourceAsStream("hibernate.Properties"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        return factory.openSession();
    }
}
