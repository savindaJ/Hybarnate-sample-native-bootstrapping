package lk.ijse.hybernate.sample.copyEntity;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetailCopy {

    @EmbeddedId
    private OrderDetailPKCopy orderDetailPK;
    @Column(name = "order_quantity")
    private int qty;
    @Column(name = "order_price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "order_id",
            referencedColumnName = "order_id",
            insertable = false,
            updatable = false)
    private OrderCopy orderCopy;
    @ManyToOne
    @JoinColumn(name = "item_id",
            referencedColumnName = "item_id",
            insertable = false,
            updatable = false)
    private ItemCopy itemCopy;

    public OrderDetailCopy() {}

    public OrderDetailCopy(OrderDetailPKCopy orderDetailPK, int qty, double price) {
        this.orderDetailPK = orderDetailPK;
        this.qty = qty;
        this.price = price;
    }

    public OrderDetailPKCopy getOrderDetailPK() {
        return orderDetailPK;
    }

    public void setOrderDetailPK(OrderDetailPKCopy orderDetailPK) {
        this.orderDetailPK = orderDetailPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

