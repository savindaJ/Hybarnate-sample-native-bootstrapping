package lk.ijse.hybernate.sample.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Column(name = "CustomerName")
    String name;
    @Column(name = "CustomerAddress")
    String address;
    @Column(name = "CustomerSalary")
    Double salary;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    Integer id;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();*/

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="Customer_ID")
    private List<Order> orders = new ArrayList<>();

    public Customer() {}

    public Customer(int id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
