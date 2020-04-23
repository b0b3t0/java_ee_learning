package service;

import model.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    @Inject
    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(String username, String email, String password, String confirmPassword) {
        entityManager.getTransaction().begin();
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);


        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
