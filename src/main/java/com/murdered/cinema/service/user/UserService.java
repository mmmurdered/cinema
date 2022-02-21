package com.murdered.cinema.service.user;

import com.murdered.cinema.dao.user.UserDao;
import com.murdered.cinema.model.user.User;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean addUser(User newUser){
        return userDao.save(newUser).getId() != -1;
    }

    public User getUser(String login, String password){
        return userDao.getUserByLoginAndPassword(login, password);
    }
}
