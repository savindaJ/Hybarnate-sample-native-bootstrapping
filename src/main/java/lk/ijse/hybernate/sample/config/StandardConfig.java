package lk.ijse.hybernate.sample.config;

import lk.ijse.hybernate.sample.entity.Customer;
import lk.ijse.hybernate.sample.entity.Item;
import lk.ijse.hybernate.sample.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StandardConfig {
    private static final SessionFactory factory;
    private static StandardConfig config;

    static {
         factory = new Configuration()
                .configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                 .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }

    private StandardConfig(){}

    public static StandardConfig getInstance(){
        return config == null ? new StandardConfig() : config;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
