package entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@ToString
public class Category {

    private int categoryid;//auto number
    private String categoryname,
            description, picture;

}
