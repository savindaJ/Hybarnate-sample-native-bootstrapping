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

    @ManyToOne
    @JoinColumn(name = "order_id",// enn ona mona primary key ekada kyla kynna ona
                referencedColumnName = "order_id",
                insertable = false,
            updatable = false
    )
    private Item item; //mapped by item in item

    @ManyToOne
    @JoinColumn(name = "code",
                referencedColumnName = "code",
                insertable = false,
            updatable = false
    )
    private Order order;

    @EmbeddedId
    private OrderDetailPK orderDetailPK;


}
