package com.company.controller;

import com.company.dto.UserDto;
import com.company.entity.Product;
import com.company.entity.User;
import com.company.service.OrderService;
import com.company.service.ProductService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Kate M on 27.03.2018.
 */

@Controller
@RequestMapping("/cart")
public class CartController {

    private UserService userService;
    private OrderService orderService;
    private ProductService productService;
    private UserDto userDto;


    @Autowired
    public CartController(UserService userService, OrderService orderService, ProductService productService, UserDto userDto) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.userDto = userDto;
    }

    @ModelAttribute("infoAboutProductsInCart")
    public Map<Product, Integer> showProductsInCart() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userService.getUserByName(name);
        Map<Product, Integer> infoAboutProductsInCart = orderService.findInfoAboutProductsInCart(foundUser.getId());

        return infoAboutProductsInCart;
    }

    @ModelAttribute("cartTotal")
    public Double showCartTotal() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userService.getUserByName(name);
        Double cartTotal = orderService.findCartTotal(foundUser.getId());
        return cartTotal;
    }

    @GetMapping(path = "/show")
    public String showCart(@RequestParam(name = "update", required = false) String result, Model model) {
        if (result != null) {
            model.addAttribute("message", "The selected amount is too big. Cart cant be updated");
        }
        model.addAttribute("title", "User Cart");
        model.addAttribute("userClickShowCart", true);
        return "index";
    }

    @GetMapping(path = "/{productId}/update")
    public String updateProductInCart(@PathVariable("productId") Long productId,
                                      @RequestParam("count") int amountToUpdate, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Product foundProduct = productService.findById(productId);
        if (amountToUpdate > foundProduct.getAmountAvailable()) {
            return "redirect:/cart/show?update=false";
        } else {
            orderService.updateProductsInUserCart(name, productId, amountToUpdate);
        }
        return "redirect:/cart/show";
    }


    @GetMapping(path = "/{productId}/delete")
    public String deleteProductInCart(@PathVariable("productId") Long productId, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        orderService.deleteProductsInUserCart(name, productId);
        return "redirect:/cart/show";
    }

    @GetMapping(path = "add/{productId}/product")
    public String addProductToCart(@PathVariable("productId") Long productId, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        orderService.addProductsToUserCart(name, productId);
        return "redirect:/cart/show";
    }


}
