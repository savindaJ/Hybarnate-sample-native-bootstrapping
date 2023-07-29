package lk.ijse.hybernate.sample.config;

import org.hibernate.Session;

public class SessionFactoryConfig {

    public static SessionFactoryConfig configuration;

    private SessionFactoryConfig(){}

    public static SessionFactoryConfig getInstance(){
        return configuration == null ? new SessionFactoryConfig() : configuration;
    }

    public Session getSession(){
        return null;
    }

}
