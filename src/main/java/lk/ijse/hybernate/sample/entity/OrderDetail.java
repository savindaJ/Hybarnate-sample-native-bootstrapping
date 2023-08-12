package lk.ijse.hybernate.sample.entity;

import lk.ijse.hybernate.sample.embeded.OrderDetailPK;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Column(name = "order_qty")
    private int qty;

    @Column(name = "order_price")
    private double price;

    @OneToMany
    private Item item;

    @OneToMany
    private Order order;

    @EmbeddedId
    private OrderDetailPK orderDetailPK;

}
