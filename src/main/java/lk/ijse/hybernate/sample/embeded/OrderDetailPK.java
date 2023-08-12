package lk.ijse.hybernate.sample.embeded;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderDetailPK implements Serializable {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "code")
    private Integer itemId;

    public OrderDetailPK() {
    }
}
