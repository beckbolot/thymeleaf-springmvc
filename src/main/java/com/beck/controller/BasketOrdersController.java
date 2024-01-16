package com.beck.controller;

import com.beck.entity.User;
import com.beck.service.BasketOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketOrdersController {

    private final BasketOrderService basketOrderService;

    @GetMapping
    public String getProductBasketForm(){
        return "basket";
    }

    @GetMapping("/add/{id}")
    public String addProductBasket(ModelMap model, @PathVariable("id") long id){
        Long userId =(Long) model.getAttribute("userId");

        if (basketOrderService.addProduct(userId,id)){
            model.addAttribute("isAdded",1);
        }else {
            model.addAttribute("isAdded",0);
        }
        return "forward:/basket";
    }

    @GetMapping("/remove/{id}")
    public String removeBasketProduct(ModelMap modelMap,@PathVariable("id") long id){
        long userId = (Long) modelMap.getAttribute("userId");
        basketOrderService.removeProduct(userId,id);
        return "redirect:/basket";
    }

    @GetMapping("/order")
    public String doOrder(Model model,HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        if (basketOrderService.doOrder(currentUser)){
            model.addAttribute("isOrdered",1);
        }else{
            model.addAttribute("isOrdered",0);
        }
        return "forward:/";
    }




    @ModelAttribute
    public void addProductBasket(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        model.addAttribute("productBasket",basketOrderService.getProductsByUserId(currentUser.getId()));
        model.addAttribute("userId",currentUser.getId());
    }
}
