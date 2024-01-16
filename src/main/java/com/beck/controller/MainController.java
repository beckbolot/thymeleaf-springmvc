package com.beck.controller;

import com.beck.entity.Product;
import com.beck.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final ProductService productService;

    @GetMapping
    public String getMain(Model model){
        model.addAttribute("productList",productService.getAllProducts());
        return "main";
    }

    @GetMapping("/sort")
    public String getSort(Model model, @PathVariable("sort") String sort){
        List<Product> productList = productService.getAllProducts();

        switch (sort.toLowerCase()){
            case "id":
                productService.orderById(productList);
                break;
            case "name":
                productService.orderByName(productList);
                break;
            case "type":
                productService.orderByType(productList);
                break;
            case "price":
                productService.orderByPrice(productList);
        }

        model.addAttribute("productList",productList);
        return "main";
    }

    @PostMapping("/search")
    public String postSearch(Model model, @RequestParam("string") String string){
        List<Product> filterProducts = productService.findProducts(string);

        if(filterProducts.isEmpty()){
            model.addAttribute("filterIsEmpty",true);
        }else {
            model.addAttribute("productList",filterProducts);
        }

        return "main";
    }

}
