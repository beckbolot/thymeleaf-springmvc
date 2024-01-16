package com.beck.controller;

import com.beck.service.FinalOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/finalOrder")
@RequiredArgsConstructor
public class FinalOrdersController {

    private final FinalOrderService finalOrderService;

    @GetMapping
    public String getOrder(Model model){
        model.addAttribute("finalOrderList",finalOrderService.getListFinalOrder());
        return "orders";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/remove/{id}")
    public String removeOrder(@PathVariable long id){
        finalOrderService.removeOrder(id);
        return "redirect:/finalOrder";
    }


}
