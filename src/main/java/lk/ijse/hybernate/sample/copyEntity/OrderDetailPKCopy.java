package lk.ijse.hybernate.sample.copyEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDetailPKCopy implements Serializable {

    @Column(name = "order_id")
    private int orderId;
    @Column(name = "item_id")
    private int itemId;

    public OrderDetailPKCopy() {}

    public OrderDetailPKCopy(int orderId, int itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
