package pl.sda.intermediate.ksiazki;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OnlyOneController {

    private CategoryService categoryService = new CategoryService();

    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) String input, Model model) {

        List<CategoryDTO> result = categoryService.findCategories(input);
        model.addAttribute("catsdata", result); //to jest "mapa" trafiająca do HTMLa
        return "catspage"; //nazwa pliku html

    }

}