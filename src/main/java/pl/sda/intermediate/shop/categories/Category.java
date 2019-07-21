package pl.sda.intermediate.shop.categories;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {

    private Integer categoryId;
    private String categoryName;
    private Integer parentId;
    private Integer level;



}
