package com.company.controller;

import com.company.entity.*;
import com.company.service.AddressService;
import com.company.service.OrderService;
import com.company.service.UserService;
import com.company.validation.RegistrationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kate M on 21.03.2018.
 */
@Controller
@SessionAttributes(types = {User.class, Address.class})
public class RegistrationController {
    private UserService userService;
    private AddressService addressService;
    private OrderService orderService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, AddressService addressService, OrderService orderService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.addressService = addressService;
        this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("user")
    public User createUser() {
        return new User();
    }

    @ModelAttribute("address")
    public Address createAddress() {
        return new Address();
    }

    @ModelAttribute("title")
    public String createTitle() {
        return new String("Sign up");
    }

    @ModelAttribute("allCountries")
    public ArrayList returnAllCountries() {
        return new ArrayList(Arrays.asList(EnumCountry.values()));
    }

    @GetMapping(path = "/register")
    public String showRegistrationPageStep1(User user, Address address, Model model) {
        return "signup-personal";
    }

    @PostMapping(path = "/register")
    public String handleUserSubmissionStep1(@Valid User user,
                                            BindingResult bindingResult, Address address,
                                            @ModelAttribute("confirmPassword") String confirmPassword,
                                            Model model) {
        new RegistrationValidation(userService).validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Validation failed for User Registration");
            return "signup-personal";
        }
        return "signup-address";
    }

    @PostMapping(path = "/register/step2")
    public String handleRegistrationPageStep2(User user, @Valid Address address,
                                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Validation failed for User Registration");
            return "signup-address";
        }
        return "signup-confirm";
    }

    @PostMapping(path = "/register/success")
    public String handleRegistrationSuccess(User user, Address address,
                                            SessionStatus sessionStatus) {
        user.setRole(EnumRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Long userId = userService.save(user);
        User savedUser = userService.findById(userId);
        address.setUser(savedUser);
        addressService.save(address);
        Orders orders = new Orders();
        orders.setStatus(EnumOrderStatus.NEW);
        orders.setUsers(savedUser);
        orders.setAddress(address);
        orderService.save(orders);
        sessionStatus.setComplete();
        return "signup-success";
    }
}
