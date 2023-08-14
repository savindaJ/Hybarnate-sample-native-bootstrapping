package lk.ijse.hybernate.sample.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_quantity")
    private Integer qty;
    @Column(name = "unit_price")
    private Double unitPrice;

//    @ManyToMany(mappedBy = "items")
//    private List<Order> orders = new ArrayList<>();

   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =  "item")
    private List<OrderDetail> orderDetails = new ArrayList<>();*/

    @ManyToMany(mappedBy = "items")
    List<Order> orders = new ArrayList<>();

    public Item() {}

    public Item(int id, String itemName, int qty, double unitPrice) {
        this.id = id;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

}
