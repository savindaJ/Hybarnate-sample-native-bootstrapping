package lk.ijse.hybernate.sample.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@Entity
@Table(schema = "item")
public class Item {
    private String itemCode;
    private String name;
    private Double price;
    private Integer qty;
}
