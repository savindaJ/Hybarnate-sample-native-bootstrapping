package lk.ijse.hybernate.sample.copyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "item")
public class ItemCopy {

    @Id
    @Column(name = "code")
    private String itemCode;
    @Column(name = "ItemName")
    private String name;
    @Column(name = "ItemPrice")
    private Double price;
    @Column(name = "ItemQty")
    private Integer qty;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "item")
    private List<OrderDetailCopy> orderDetailCopies = new ArrayList<>();

    public ItemCopy(String itemCode, String name, Double price, Integer qty) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
}
