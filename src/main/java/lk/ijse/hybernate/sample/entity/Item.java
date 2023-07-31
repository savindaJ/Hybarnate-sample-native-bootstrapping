package lk.ijse.hybernate.sample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@Entity
@Table(schema = "item")
public class Item {
    @Id
    @Column(name = "code")
    private String itemCode;
    @Column(name = "ItemName")
    private String name;
    @Column(name = "ItemPrice")
    private Double price;
    @Column(name = "ItemQty")
    private Integer qty;
}
