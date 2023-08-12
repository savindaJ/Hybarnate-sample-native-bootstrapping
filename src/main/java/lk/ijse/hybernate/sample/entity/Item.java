package lk.ijse.hybernate.sample.entity;

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


    /*@ManyToMany(mappedBy = "items")
    private List<Order> orders =  new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();


}
