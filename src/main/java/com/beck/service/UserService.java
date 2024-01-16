package com.beck.service;

import com.beck.entity.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

    boolean registration(User user);

    User authorization(User user);

    void logout (HttpSession session);
}
