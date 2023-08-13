package lk.ijse.hybernate.sample.entity;

import lk.ijse.hybernate.sample.embeded.OrderDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
@Data
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    @Column(name = "order_quantity")
    private int qty;
    @Column(name = "order_price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id",
                referencedColumnName = "order_id",
                insertable = false,
                updatable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "item_id",
                referencedColumnName = "item_id",
                insertable = false,
                updatable = false)
    private Item item;

    public OrderDetail(OrderDetailPK orderDetailPK, int qty, double price) {
        this.orderDetailPK = orderDetailPK;
        this.qty = qty;
        this.price = price;
    }
}
