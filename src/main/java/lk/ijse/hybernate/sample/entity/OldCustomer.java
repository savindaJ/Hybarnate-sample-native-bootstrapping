package lk.ijse.hybernate.sample.entity;

import lk.ijse.hybernate.sample.embeded.NameIdentifier;
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
public class OldCustomer {
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

   /* @Column(name = "customer_age" ,columnDefinition = "TinyInt")
    private int age;*/

    /*private NameIdentifier nameIdentifier;*/

    /*embeddable class usage !*/
    /*
    private NameIdentifier nameIdentifier;
    cretae column in this customer table !
    */
    /*
     *  embedded usage
     *  new NameIdentifier
     *  set setters and set object in object !
     */
    /* private NameIdentifier nameIdentifier;*/

   /* @ElementCollection
            @CollectionTable(
                    name = "numbers",
                    joinColumns = @JoinColumn(name = "customerID")
            )
    List<MobileNo> mobileNos = new ArrayList<>();*/

   /* Customer customer = new Customer();

    void sample(){
        MobileNo mobileNo = new MobileNo();
        mobileNo.setMobileLine("022144555555");
        customer.setMobi    leNos(mobileNo);
    }*/

    /*generative type*/
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE)*/
    /*@GeneratedValue(strategy = GenerationType.TABLE)*/


}



