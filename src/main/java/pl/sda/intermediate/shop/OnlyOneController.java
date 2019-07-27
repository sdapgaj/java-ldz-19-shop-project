package pl.sda.intermediate.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.intermediate.shop.categories.CategoryDTO;
import pl.sda.intermediate.shop.categories.CategoryService;
import pl.sda.intermediate.shop.login.LoginDTO;
import pl.sda.intermediate.shop.login.LoginService;
import pl.sda.intermediate.shop.registration.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    private CategoryService categoryService = new CategoryService();
    private ValidationService validationService = new ValidationService();
    private UserDAO userDAO = new UserDAO();
    private RegistrationService registrationService = new RegistrationService(userDAO);
    private LoginService loginService = new LoginService(userDAO);

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
        model.addAttribute("form", registrationDTO);
        model.addAttribute("countries", Countries.values());

        if (mapOfErrors.isEmpty()) {
            // rejestrujemny
            try {
                registrationService.register(registrationDTO);
            } catch (UserExistsException e) {
                model.addAttribute("userExistsExceptionMessage", e.getMessage());
                return "registerPage";
            }
            return "helloPage";
        } else {
            model.addAllAttributes(mapOfErrors);
            return "registerPage";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        model.addAttribute("form", new LoginDTO());
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST) //to jest metoda do przechwytywania danych
    public String login(@ModelAttribute LoginDTO loginDTO, Model model) {

        if (loginService.login(loginDTO)) {
            return "index";
        } else {
            model.addAttribute("form", loginDTO);
            model.addAttribute("error", "Login error");
            return "loginPage";
        }

    }

}