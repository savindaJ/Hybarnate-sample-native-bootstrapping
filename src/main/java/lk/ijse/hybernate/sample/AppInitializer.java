package lk.ijse.hybernate.sample;

import lk.ijse.hybernate.sample.config.SessionFactoryConfig;
import lk.ijse.hybernate.sample.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppInitializer {
    public static void main(String[] args) {
       /* Customer customer = new Customer();
        customer.setAddress("matara");
        customer.setContact(200.78);
        customer.setName("kamal");
        customer.setId("S001");
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();*/

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer s001 = session.get(Customer.class, "S001");
        s001.setName("SAVINDA");
        s001.setAddress("Dickwalla");
        session.save(s001);
        transaction.commit();
        session.close();


    }
}
