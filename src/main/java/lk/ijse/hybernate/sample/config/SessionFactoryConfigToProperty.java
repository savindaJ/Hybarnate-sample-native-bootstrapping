package lk.ijse.hybernate.sample.config;

import lk.ijse.hybernate.sample.copyEntity.CustomerCopy;
import lk.ijse.hybernate.sample.copyEntity.ItemCopy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfigToProperty {
    private static final SessionFactory factory;
    private static SessionFactoryConfigToProperty configToProperty;

    static {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.Properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        configuration.addAnnotatedClass(CustomerCopy.class).addAnnotatedClass(ItemCopy.class);
        factory=configuration.setProperties(properties).buildSessionFactory();
    }

    private SessionFactoryConfigToProperty(){
        // toDo : pass to static !
    }

    public static SessionFactoryConfigToProperty getInstance(){
        return configToProperty == null ? new SessionFactoryConfigToProperty() : configToProperty;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
