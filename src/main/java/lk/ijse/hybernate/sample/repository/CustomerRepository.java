package lk.ijse.hybernate.sample.repository;

import lk.ijse.hybernate.sample.config.StandardConfig;
import lk.ijse.hybernate.sample.entity.Customer;
import org.hibernate.Session;


public class CustomerRepository {
    {
       session = StandardConfig.getInstance().getSession();
    }
    Session session;
    public Customer getCustomer(String id){
       return session.get(Customer.class,id);
    }
}
