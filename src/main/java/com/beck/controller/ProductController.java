package com.beck.controller;

import com.beck.entity.Product;
import com.beck.entity.ProductTypeEnum;
import com.beck.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProductForm(Model model){
        model.addAttribute("product",new Product());
        return "create";
    }

    @PostMapping("/create")
    public String addProduct(Model model, @Valid @ModelAttribute Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "create";
        }else {
            productService.createProduct(product);
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String getProductEditForm(Model model, @PathVariable("id") long id){
        model.addAttribute("product",productService.getProductById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String postEdit(Model model,@Valid @ModelAttribute Product product,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "edit";
        }else {
            productService.updateProduct(product);
            return "redirect:/";
        }
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable long id){
        productService.removeProduct(id);
        return "redirect:/";
    }


    @ModelAttribute("types")
    public ProductTypeEnum[] addAttributes(){
        return ProductTypeEnum.values();
    }
}
