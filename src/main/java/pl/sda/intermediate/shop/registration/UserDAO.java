package pl.sda.intermediate.shop.registration;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    List<User> users = new ArrayList<>();

    public void addNewUser(User user) {

        users.add(user);

    }

    boolean checkIfUserExistsByEmail(String email) {

        for (User user : users) {
            if (user.getEMail().equals(email)) {
                return true;
            }
        }

        return false;

//        return users.stream().anyMatch(u -> u.getEMail().equals(email));

    }
}
