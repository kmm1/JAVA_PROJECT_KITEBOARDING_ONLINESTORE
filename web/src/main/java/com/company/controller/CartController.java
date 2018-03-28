package com.company.controller;

import com.company.dto.UserDto;
import com.company.entity.Product;
import com.company.service.OrderService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Kate M on 27.03.2018.
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    private UserService userService;
    private OrderService orderService;
    private UserDto userDto;

    @Autowired
    public CartController(UserService userService, OrderService orderService, UserDto userDto) {
        this.userService = userService;
        this.orderService = orderService;
        this.userDto = userDto;
    }

    @ModelAttribute("infoAboutProductsInCart")
    public Map<Product, Integer> showProductsInCart() {
        Map<Product, Integer> infoAboutProductsInCart = orderService.findInfoAboutProductsInCart(4L);

        return infoAboutProductsInCart;
    }



    @ModelAttribute("cartTotal")
    public Double showCrtTotal() {
        Double cartTotal = orderService.findCartTotal(4L);
        return cartTotal;
    }


    @GetMapping(path = "/show")
    public String showCart(Model model) {
        model.addAttribute("title", "User Cart");
        model.addAttribute("userClickShowCart", true);
        //  model.addAttribute("cartLines", null);
        return "index";
    }
}
