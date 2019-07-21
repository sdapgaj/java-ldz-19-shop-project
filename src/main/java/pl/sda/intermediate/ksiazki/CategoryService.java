package pl.sda.intermediate.ksiazki;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryService {

    public List<CategoryDTO> findCategories(String searchText) {

        CategoryDAO categoryDAO = CategoryDAO.getInstance();

        Map<Integer, CategoryDTO> dtosMap = categoryDAO.getCategoryLIst().stream()
                .map(e -> convertToCategoryDTO(e))
                .collect(Collectors.toMap(e -> e.getId(), e -> e));

        dtosMap.values().stream()
                .filter(e -> e.getName().equals(StringUtils.defaultIfBlank(searchText, "").trim()))
                .forEach(foundCategory -> {
                    foundCategory.makAsSelected();
                    openParent(foundCategory, dtosMap);
                });

        return new ArrayList<>(dtosMap.values());

    }

    private void openParent(CategoryDTO categoryDTO, Map<Integer, CategoryDTO> dtos) {

        Integer parentId = categoryDTO.getParentId();

        if (parentId == null) {
            return;
        }

        CategoryDTO parent = dtos.get(parentId);
        parent.makAsOpened();
        openParent(parent, dtos);

    }

    private CategoryDTO convertToCategoryDTO(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getCategoryId());
        categoryDTO.setParentId(category.getParentId());
        categoryDTO.setName(category.getCategoryName());
        categoryDTO.setState(new CategoryState(false, false));
        return categoryDTO;

    }

}
