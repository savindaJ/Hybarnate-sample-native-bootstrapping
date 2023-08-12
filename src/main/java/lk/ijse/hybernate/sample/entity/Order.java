package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

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

}
