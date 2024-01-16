package com.beck.service;

import com.beck.dao.UserDao;
import com.beck.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{


    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean registration(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User authorization(User user) {
        return userDao.getUser(user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public void logout(HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        session.invalidate();
        userDao.logout(currentUser);

    }
}
