package com.murdered.cinema.service.user;

import com.murdered.cinema.dao.user.UserDao;
import com.murdered.cinema.model.user.User;
import org.apache.log4j.Logger;

public class UserService {
    private static Logger logger = Logger.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User addUser(User newUser){
        logger.info("Adding user");
        return userDao.save(newUser);
    }

    public User getUserByLoginPass(String login, String password){
        logger.info("Getting user by password and login");
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public User getUserById(long id){
        logger.info("Getting user by id");
        return userDao.get(id);
    }

    public User getUserByLogin(String login){
        logger.info("Getting user by login");
        return userDao.getUserByLogin(login);
    }

    public User getUserByEmail(String email){
        logger.info("Getting user by email");
        return userDao.getUserByEmail(email);
    }
}
