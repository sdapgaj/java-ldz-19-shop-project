package pl.sda.intermediate.shop.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class DBUserDAO implements UserDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addNewUser(User user) {

    }

    @Override
    public boolean checkIfUserExistsByEmail(String eMail) {
        return false;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.empty();
    }
}
