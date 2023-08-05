package lk.ijse.hybernate.sample.repository;

import lk.ijse.hybernate.sample.config.StandardConfig;
import lk.ijse.hybernate.sample.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;


public class CustomerRepository {
    {
       session = StandardConfig.getInstance().getSession();
    }
    private final Session session;
    public Customer getCustomer(String id){
       return session.get(Customer.class,id);
    }

    public boolean saveCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();

        /*returning customer id in serializable type*/
        Serializable save = session.save(customer);

        transaction.commit();

        session.close();

        return save != null;
    }

    public boolean updateCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();

        session.update(customer);

        transaction.commit();

        session.close();

        return true;
    }

    public boolean deleteCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();

        session.delete(customer);

        transaction.commit();

        session.close();

        return true;
    }
}
