package lk.ijse.hybernate.sample.entity;

import lk.ijse.hybernate.sample.embeded.NameIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "customer")
public class Customer {

    /**
     * add new customer class
     * and relationship adding !
     * */

    @Column(name = "CustomerName")
    String name;
    @Column(name = "CustomerAddress")
    String address;
    @Column(name = "CustomerSalary")
    Double salary;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    int id;

//    toDo : create relationship !

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    public Customer(String name, String address, Double salary, int id) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.id = id;
    }
}
