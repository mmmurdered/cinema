package com.murdered.cinema.dao.user;

import com.murdered.cinema.dao.DAO;
import com.murdered.cinema.model.user.User;

public interface UserDao extends DAO<User> {
    public User getUserByLoginAndPassword(String login, String password);

    public User getUserByLogin(String login);

    public User getUserByEmail(String email);



}
