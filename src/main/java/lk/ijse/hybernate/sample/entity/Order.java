package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id" ,length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderID;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "order_date")
    private Timestamp orderDate;

//    toDo : relate customer and order !

    @ManyToOne
            @JoinColumn(name = "customerID") // joining column name in customer !
    Customer customer;

    /*@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();



}
