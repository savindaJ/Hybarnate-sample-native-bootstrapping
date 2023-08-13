package lk.ijse.hybernate.sample.copyEntity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class OrderCopy {

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
    private CustomerCopy customer;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Item> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetailCopy> orderDetailCopies = new ArrayList<>();

    public OrderCopy() {}

    public OrderCopy(int id, String description, Timestamp orderDateTime) {
        this.id = id;
        this.description = description;
        this.orderDateTime = orderDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Timestamp orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
}
