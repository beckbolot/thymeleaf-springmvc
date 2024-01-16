package com.beck.interceptor;

import com.beck.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class DispatcherInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        if (currentUser == null || !currentUser.isDispatcher()){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
