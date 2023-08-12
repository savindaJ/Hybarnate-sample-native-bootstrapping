package lk.ijse.hybernate.sample.embeded;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class OrderDetailPK {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "item_id")
    private Integer itemId;

    public OrderDetailPK() {
    }
}
