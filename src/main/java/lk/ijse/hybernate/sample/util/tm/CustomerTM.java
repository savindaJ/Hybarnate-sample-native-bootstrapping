package lk.ijse.hybernate.sample.util.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerTM {
    String name;
    String address;
    Double salary;
    int id;
}
