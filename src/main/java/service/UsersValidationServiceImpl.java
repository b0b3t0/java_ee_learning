package service;

import model.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersValidationServiceImpl implements UsersValidationService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final EntityManager entityManager;

    @Inject
    public UsersValidationServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public boolean canCreateUser(String username, String email, String password, String confirmPassword) {
        return isEmailValid(email) &&
                arePasswordMatching(password, confirmPassword)  &&
                !isUsernameTaken(username);
    }

    private boolean isUsernameTaken(String username) {

        List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();

        return !users.isEmpty();
    }

    public static boolean isEmailValid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean arePasswordMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
