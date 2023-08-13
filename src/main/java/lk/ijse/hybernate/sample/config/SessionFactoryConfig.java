package lk.ijse.hybernate.sample.config;

import lk.ijse.hybernate.sample.copyEntity.CustomerCopy;
import lk.ijse.hybernate.sample.copyEntity.ItemCopy;
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

        //toDo : complex bootstrapping !

        StandardServiceRegistry builder = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(builder).addAnnotatedClass(CustomerCopy.class).addAnnotatedClass(ItemCopy.class).
                getMetadataBuilder().
                applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE).
                build();


        SessionFactory sessionFactory = meta.buildSessionFactory();

        //toDo : simplify in this native bootstrapping !

        SessionFactory factory = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build())
                .addAnnotatedClass(CustomerCopy.class)
                .addAnnotatedClass(ItemCopy.class).
                getMetadataBuilder().
                applyImplicitNamingStrategy(ImplicitNamingStrategyComponentPathImpl.INSTANCE).
                build()
                .buildSessionFactory();

//        return sessionFactory.openSession();
        return factory.openSession();
    }

}
