package com.beck.dao;

import com.beck.entity.User;

public interface UserDao {

    boolean createUser(User user);

    User getUser(String email, String password);

    void logout(User user);
}
