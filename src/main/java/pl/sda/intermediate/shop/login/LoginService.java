package pl.sda.intermediate.shop.login;

import org.apache.commons.codec.digest.DigestUtils;
import pl.sda.intermediate.shop.registration.User;
import pl.sda.intermediate.shop.registration.UserDAO;

public class LoginService {

    private UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(LoginDTO loginDTO) {

        boolean ableToLogin = userDAO
                .findUserByEmail(loginDTO.getLogin())
                .map( u -> compareHashes(loginDTO, u))
                .orElse(false);

        // powyżej
        // jesli przychodzi otional to map wykona się wkiedy będzie true a jes nie to wykona się orelse

        if (ableToLogin) {
            UserContextHolder.addLoggedUser(loginDTO);
        }

        return ableToLogin;

    }

    private boolean compareHashes(LoginDTO loginDTO, User u) {
        return u.getPasswordHash().equals(DigestUtils.sha512Hex(loginDTO.getPassword()));
    }

}
