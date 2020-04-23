package service;

import model.entity.User;
import model.service.UserServiceModel;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final HashingService hashingService;
    private final ModelMapper modelMapper;
    private final UsersValidationService usersValidationService;

    @Inject
    public UserServiceImpl(EntityManager entityManager, HashingService hashingService, ModelMapper modelMapper, UsersValidationService usersValidationService) {
        this.entityManager = entityManager;
        this.hashingService = hashingService;
        this.modelMapper = modelMapper;
        this.usersValidationService = usersValidationService;
    }

    @Override
    public void register(String username, String email, String password, String confirmPassword) throws Exception {
        if(!usersValidationService.canCreateUser(username, email, password, confirmPassword)) {
            throw new Exception("User cannot be createad!");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(hashingService.hash(password));
        user.setPassword(password);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public UserServiceModel login(String username, String password) {
        List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        if(users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        if (!user.getPassword().equals(hashingService.hash(password))) {
            return null;
        }

        return modelMapper.map(user, UserServiceModel.class);
    }
}
