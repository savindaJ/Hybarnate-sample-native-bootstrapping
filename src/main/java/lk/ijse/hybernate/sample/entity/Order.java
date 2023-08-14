package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    @Column(name = "order_description")
    private String description;
    @CreationTimestamp
    @Column(name = "order_date")
    private Timestamp orderDateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Item> items = new ArrayList<>();

   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();
*/

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_detail",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "code")
    )
    List<Item> items; // this reference use to mapped attribute !


    public Order(int id, String description, Timestamp orderDateTime) {
        this.id = id;
        this.description = description;
        this.orderDateTime = orderDateTime;
    }
}
