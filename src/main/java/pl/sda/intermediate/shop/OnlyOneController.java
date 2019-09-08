package pl.sda.intermediate.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.intermediate.shop.categories.CategoryDTO;
import pl.sda.intermediate.shop.categories.CategoryService;
import pl.sda.intermediate.shop.login.LoginDTO;
import pl.sda.intermediate.shop.login.LoginService;
import pl.sda.intermediate.shop.login.UserContextHolder;
import pl.sda.intermediate.shop.registration.*;
import pl.sda.intermediate.shop.weather.WeatherService;
import pl.sda.intermediate.shop.weather.WeatherWrapper;

import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private ValidationService validationService;

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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        UserContextHolder.delLoggedUser();
        model.addAttribute("form", new LoginDTO());
        return "loginPage";
    }

    @ResponseBody
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public WeatherWrapper weather() {
        WeatherWrapper weatherWrapper = weatherService.downloadWeather();
        return weatherWrapper;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/registration", method = RequestMethod.POST)
    public ResponseEntity registrationApi(@RequestBody RegistrationDTO registrationDTO) {
        System.out.println(registrationDTO);
        Map<String, String> mapOfErrors = validationService.validateUserData(registrationDTO);
        if (mapOfErrors.isEmpty()) {
            try {
                registrationService.register(registrationDTO);
            } catch (UserExistsException e) {
                return ResponseEntity.status(400).body(e.getMessage());
            }
            return ResponseEntity.ok("Registered");
        } else {
            return ResponseEntity.status(400).body(mapOfErrors);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        if (loginService.login(loginDTO)) {
            return ResponseEntity.ok("Logged-in");
        } else {
            return ResponseEntity.status(400).body("Login failed");
        }
    }

}
