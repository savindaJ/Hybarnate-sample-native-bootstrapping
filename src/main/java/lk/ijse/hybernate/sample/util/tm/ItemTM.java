package lk.ijse.hybernate.sample.util.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemTM {
    private String itemCode;
    private String name;
    private Double price;
    private Integer qty;
}
