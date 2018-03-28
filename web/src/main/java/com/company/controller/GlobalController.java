package com.company.controller;

import com.company.dto.UserDto;
import com.company.entity.EnumOrderStatus;
import com.company.entity.Orders;
import com.company.entity.Product;
import com.company.entity.User;
import com.company.service.OrderService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by Kate M on 26.03.2018.
 */
@ControllerAdvice
@SessionAttributes(types = {UserDto.class})
public class GlobalController {


    private UserService userService;
    private OrderService orderService;

    @Autowired
    public GlobalController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    private UserDto userDto;


    @ModelAttribute("userDto")
    public UserDto getUserModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User foundUser = userService.getUserByName(authentication.getName());
        if (foundUser != null) {
            Long numberOfProductsInOrder = orderService.countProductsInCart(foundUser.getId());
            Orders currentOrder = orderService.findUserCart(foundUser.getId());
            List<Product> products = currentOrder.getProducts();
            Double sum = products.stream().mapToDouble(Product::getPrice).sum();
            userDto = new UserDto(foundUser.getId(), foundUser.getName(),
                    foundUser.getRole(), numberOfProductsInOrder, currentOrder, sum);
        }
        return userDto;
    }
}
