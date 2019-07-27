package pl.sda.intermediate.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.intermediate.shop.categories.CategoryDTO;
import pl.sda.intermediate.shop.categories.CategoryService;
import pl.sda.intermediate.shop.registration.Countries;
import pl.sda.intermediate.shop.registration.RegistrationDTO;
import pl.sda.intermediate.shop.registration.RegistrationService;
import pl.sda.intermediate.shop.registration.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    private CategoryService categoryService = new CategoryService();
    private ValidationService validationService = new ValidationService();
    private RegistrationService registrationService = new RegistrationService();

    @RequestMapping("/categories")
    public String categories(@RequestParam(required = false) String input, Model model) {

        List<CategoryDTO> result = categoryService.findCategories(input);
        model.addAttribute("catsdata", result); //to jest "mapa" trafiająca do HTMLa
        return "catspage"; //nazwa pliku html

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("form", new RegistrationDTO());
        model.addAttribute("countries", Countries.values());
        return "registerPage";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST) //to jest metoda do przechwytywania danych
    public String registration(@ModelAttribute RegistrationDTO registrationDTO, Model model) {

        Map<String, String> mapOfErrors = validationService.validateUserData(registrationDTO);
        if (mapOfErrors.isEmpty()) {
            // rejestrujemny
            registrationService.register(registrationDTO);
            return "helloPage";
        } else {
            model.addAllAttributes(mapOfErrors);
            model.addAttribute("form", registrationDTO);
            return "registerPage";
        }

    }

}