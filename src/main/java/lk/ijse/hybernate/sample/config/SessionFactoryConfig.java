package lk.ijse.hybernate.sample.config;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class SessionFactoryConfig {

    public static SessionFactoryConfig configuration;

    private SessionFactoryConfig(){}

    public static SessionFactoryConfig getInstance(){
        return configuration == null ? new SessionFactoryConfig() : configuration;
    }

    public Session getSession(){

        StandardServiceRegistry builder = new StandardServiceRegistryBuilder().configure().build();

        MetadataSources metadataSources = new MetadataSources(builder);

        return null;
    }

}
