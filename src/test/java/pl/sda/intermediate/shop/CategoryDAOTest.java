package pl.sda.intermediate.shop;


import org.junit.jupiter.api.Test;
import pl.sda.intermediate.shop.categories.Category;
import pl.sda.intermediate.shop.categories.CategoryDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDAOTest {

    @Test
    public void shouldPopulateProperty() {

        Category testCategory = new Category();
        testCategory.setParentId(4);
        testCategory.setCategoryId(6);
        testCategory.setLevel(2);
        testCategory.setCategoryName("Klasa druga");

        CategoryDAO categoryDAO = CategoryDAO.getInstance();
        Category category = categoryDAO.getCategoryLIst()
                .stream()
                .filter(i -> i.getCategoryId().equals(6))
                .findFirst()
                .orElse(null);

        assertEquals(testCategory.getLevel(), category.getLevel());
        assertEquals(testCategory.getParentId(), category.getParentId());

    }
}
