package lk.ijse.hybernate.sample.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StandardConfig {
    private static SessionFactory factory;

    private static StandardConfig config;

    static {

    }

    private StandardConfig(){}

    public static StandardConfig getInstance(){
        return config == null ? new StandardConfig() : config;
    }

    public Session getSession(){
        return factory.openSession();
    }
}
