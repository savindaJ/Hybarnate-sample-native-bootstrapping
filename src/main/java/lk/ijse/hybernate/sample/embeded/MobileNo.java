package lk.ijse.hybernate.sample.embeded;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class MobileNo {
    private String lanLine;
    private String mobileLine;
}
