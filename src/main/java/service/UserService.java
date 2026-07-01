package service;

import model.User;

public interface UserService {
    void register(String name, String email, String phone, String password, String confirmPassword, String role);
    User login(String email, String password);
}
