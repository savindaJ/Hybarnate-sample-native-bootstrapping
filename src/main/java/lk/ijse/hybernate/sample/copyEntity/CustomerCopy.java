package lk.ijse.hybernate.sample.copyEntity;

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
public class CustomerCopy {

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
    private List<OrderCopy> orderCopies = new ArrayList<>();

    public CustomerCopy(String name, String address, Double salary, int id) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.id = id;
    }
}
