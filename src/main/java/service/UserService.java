package service;

import model.service.UserServiceModel;

public interface UserService {

    void register(String username, String email, String password, String confirmPassword) throws Exception;

    UserServiceModel login(String username, String password);
}
