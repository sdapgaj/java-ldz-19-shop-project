package pl.sda.intermediate.shop.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
//@Primary --> dodana adnotacja w RegistrationService
public class DBUserDAO implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addNewUser(User user) {
        entityManager.persist(user.getAddress());
        entityManager.persist(user);
    }

    @Override
    public boolean checkIfUserExistsByEmail(String eMail) {
        List<User> listOfUsersWithSameEmail = entityManager.createQuery("SELECT u FROM User u WHERE u.eMail = :eMail", User.class)
                .setParameter("eMail", eMail).getResultList();

        return !listOfUsersWithSameEmail.isEmpty();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.eMail = :eMail", User.class).setParameter("eMail", email).getResultList();

        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }
}
