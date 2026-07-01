package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import exception.AppException;
import model.User;
import service.UserService;
import util.PasswordUtil;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void register(String name, String email, String phone, String password, String confirmPassword, String role) {
        if (isBlank(name)) throw new AppException("請輸入姓名");
        if (isBlank(email)) throw new AppException("請輸入 Email");
        if (!email.contains("@")) throw new AppException("Email 格式不正確");
        if (isBlank(password) || password.length() < 6) throw new AppException("密碼至少需要 6 碼");
        if (!password.equals(confirmPassword)) throw new AppException("兩次輸入的密碼不一致");
        if (userDao.emailExists(email)) throw new AppException("此 Email 已註冊，請直接登入");
        if (isBlank(role)) role = "飼主";

        User user = new User();
        user.setName(name.trim());
        user.setEmail(email.trim().toLowerCase());
        user.setPhone(phone == null ? "" : phone.trim());
        user.setPasswordHash(PasswordUtil.hash(password));
        user.setRole(role);
        userDao.create(user);
    }

    @Override
    public User login(String email, String password) {
        if (isBlank(email)) throw new AppException("請輸入 Email");
        if (isBlank(password)) throw new AppException("請輸入密碼");
        User user = userDao.login(email.trim().toLowerCase(), PasswordUtil.hash(password));
        if (user == null) throw new AppException("帳號或密碼錯誤");
        return user;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
