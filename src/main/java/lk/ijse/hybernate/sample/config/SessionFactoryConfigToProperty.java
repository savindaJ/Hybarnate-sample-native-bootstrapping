package lk.ijse.hybernate.sample.config;

import org.hibernate.Session;

public class SessionFactoryConfigToProperty {
    private static SessionFactoryConfigToProperty configToProperty;

    private SessionFactoryConfigToProperty(){

    }

    public static SessionFactoryConfigToProperty getInstance(){
        return configToProperty == null ? new SessionFactoryConfigToProperty() : configToProperty;
    }

    public static Session getSession(){
        return null;
    }
}
