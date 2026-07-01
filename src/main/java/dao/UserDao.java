package dao;

import model.User;

public interface UserDao {
    void create(User user);
    User findByEmail(String email);
    User login(String email, String passwordHash);
    boolean emailExists(String email);
}
