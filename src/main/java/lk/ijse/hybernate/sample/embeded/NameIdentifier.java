package lk.ijse.hybernate.sample.embeded;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class NameIdentifier {
    private String fName;
    private String lName;
    private String midName;
}
