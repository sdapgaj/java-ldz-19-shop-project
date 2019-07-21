package pl.sda.intermediate.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.intermediate.shop.categories.CategoryDTO;
import pl.sda.intermediate.shop.categories.CategoryService;
import pl.sda.intermediate.shop.registration.RegistrationDTO;
import pl.sda.intermediate.shop.registration.ValidationService;

import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    private CategoryService categoryService = new CategoryService();
    private ValidationService validationService = new ValidationService();

    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) String input, Model model) {

        List<CategoryDTO> result = categoryService.findCategories(input);
        model.addAttribute("catsdata", result); //to jest "mapa" trafiająca do HTMLa
        return "catspage"; //nazwa pliku html

    }

    @RequestMapping("/registration")
    public String registration(@ModelAttribute RegistrationDTO registrationDTO, Model model) {

        Map<String, String> mapOfErrors = validationService.validateUserData(registrationDTO);
        if (mapOfErrors.isEmpty()) {
            // rejestrujemny
            return "helloPage";
        } else {
            model.addAllAttributes(mapOfErrors);
            return "registerPage";
        }

    }

}