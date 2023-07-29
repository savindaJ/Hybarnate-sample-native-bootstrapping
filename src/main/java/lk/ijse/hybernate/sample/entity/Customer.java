package lk.ijse.hybernate.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*identify entity*/
@Entity
/*naming table*/
@Table(schema = "customer")
public class Customer {
    @Column(name = "CustomerName")
    String name;
    @Column(name = "CustomerAddress")
    String address;
    @Column(name = "CustomerSalary")
    Double salary;

    @Id //identify primary key
    @Column(name = "customerID")  // identify column
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getContact() {
        return salary;
    }

    public void setContact(Double contact) {
        this.salary = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
