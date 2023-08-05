package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")  // identify column
    int id;

                      /*generative type*/
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE)*/
    /*@GeneratedValue(strategy = GenerationType.TABLE)*/


}
