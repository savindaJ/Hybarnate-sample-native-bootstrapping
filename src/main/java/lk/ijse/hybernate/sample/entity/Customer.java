package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
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

}
