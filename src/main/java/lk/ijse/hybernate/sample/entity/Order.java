package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@AllArgsConstructor
@Data

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id" ,length = 30)
    private String orderID;

    @Column(name = "order_date")
    private Date orderDate;

}
