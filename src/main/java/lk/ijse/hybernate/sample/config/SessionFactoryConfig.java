package lk.ijse.hybernate.sample.config;

import lk.ijse.hybernate.sample.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
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
        Metadata meta = new MetadataSources(builder).addAnnotatedClass(Customer.class).
                getMetadataBuilder().
                applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE).
                build();


        SessionFactory sessionFactory = meta.buildSessionFactory();
        return sessionFactory.openSession();
    }

}
