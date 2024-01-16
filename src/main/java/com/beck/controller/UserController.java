package com.beck.controller;

import com.beck.entity.User;
import com.beck.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String getRegistrationForm(Model model) {
        model.addAttribute("regUser", new User());
        return "registration";
    }

    @PostMapping("/reg")
    public String postRegistration(@Valid @ModelAttribute("regUser") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        } else if (userService.registration(user)) {
            return "redirect:/user/auth";
        } else {
            model.addAttribute("isExist", true);
            return "registration";
        }
    }

    @GetMapping("/auth")
    public String getAuthorization(Model model){
        model.addAttribute("authUser",new User());
        return "authorization";
    }

    @PostMapping("/auth")
    public String postAuthorization(@ModelAttribute("authUser") User user, Model model, HttpSession session){
        User originalUser = userService.authorization(user);

        if (originalUser !=null){
            session.setAttribute("currentUser",originalUser);
            return "redirect:/";
        }else {
            model.addAttribute("isFail",true);
            return "authorization";
        }
    }

    @GetMapping("/out")
    public String logout(HttpSession session){
        userService.logout(session);
        return "redirect:/";
    }




}
