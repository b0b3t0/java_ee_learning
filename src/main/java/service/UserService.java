package service;

public interface UserService {

    void create(String username, String email, String password, String confirmPassword);
}
