package com.murdered.cinema.service.user;

import com.murdered.cinema.dao.user.UserDao;
import com.murdered.cinema.dao.user.UserDaoImpl;
import com.murdered.cinema.model.user.User;
import com.murdered.cinema.model.user.UserRole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init(){
        userService = new UserService(userDao);
    }

    @Test
    void addUserTest() {
        User user = new User(1, "testUser", "testmail@gmail.com", "testPass", UserRole.REGISTERED_USER);

        userService.addUser(user);

        verify(userDao, times(1)).save(user);
    }

    @Test
    void getUserByLoginPassTest() {
        User user = new User(1, "testUser", "testmail@gmail.com", "testPass", UserRole.REGISTERED_USER);
        when(userDao.getUserByLoginAndPassword(user.getLogin(), user.getPassword())).thenReturn(user);

        User resultUser = userService.getUserByLoginPass(user.getLogin(), user.getPassword());

        Assert.assertEquals(resultUser, user);
        verify(userDao, times(1)).getUserByLoginAndPassword(user.getLogin(), user.getPassword());
    }

    @Test
    void getUserByIdTest() {
        List<User> testUserList = new ArrayList<>();
        testUserList.add(new User(1, "testUser1", "testmail1@gmail.com", "testPass1", UserRole.REGISTERED_USER));
        testUserList.add(new User(2, "testUser2", "testmail2@gmail.com", "testPass2", UserRole.REGISTERED_USER));
        testUserList.add(new User(3, "testUser3", "testmail3@gmail.com", "testPass3", UserRole.REGISTERED_USER));
        when(userDao.get(1)).thenReturn(testUserList.get(0));

        User resultUser = userService.getUserById(1);

        Assert.assertEquals(resultUser, testUserList.get(0));
        verify(userDao, times(1)).get(1);
    }

    @Test
    void getUserByLoginTest() {
        List<User> testUserList = new ArrayList<>();
        testUserList.add(new User(1, "testUser1", "testmail1@gmail.com", "testPass1", UserRole.REGISTERED_USER));
        testUserList.add(new User(2, "testUser2", "testmail2@gmail.com", "testPass2", UserRole.REGISTERED_USER));
        testUserList.add(new User(3, "testUser3", "testmail3@gmail.com", "testPass3", UserRole.REGISTERED_USER));
        when(userDao.getUserByLogin("testUser2")).thenReturn(testUserList.get(1));

        User resultUser = userService.getUserByLogin("testUser2");

        Assert.assertEquals(resultUser, testUserList.get(1));
        verify(userDao, times(1)).getUserByLogin("testUser2");
    }

    @Test
    void getUserByEmailTest() {
        List<User> testUserList = new ArrayList<>();
        testUserList.add(new User(1, "testUser1", "testmail1@gmail.com", "testPass1", UserRole.REGISTERED_USER));
        testUserList.add(new User(2, "testUser2", "testmail2@gmail.com", "testPass2", UserRole.REGISTERED_USER));
        testUserList.add(new User(3, "testUser3", "testmail3@gmail.com", "testPass3", UserRole.REGISTERED_USER));
        when(userDao.getUserByEmail("testmail2@gmail.com")).thenReturn(testUserList.get(1));

        User resultUser = userService.getUserByEmail("testmail2@gmail.com");

        Assert.assertEquals(resultUser, testUserList.get(1));
        verify(userDao, times(1)).getUserByEmail("testmail2@gmail.com");
    }
}