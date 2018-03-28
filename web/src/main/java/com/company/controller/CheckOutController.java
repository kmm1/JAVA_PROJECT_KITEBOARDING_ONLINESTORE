package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Kate M on 28.03.2018.
 */

@Controller
public class CheckOutController {

    @GetMapping("/checkout")
    public String showErrorPage(Model model) {
        model.addAttribute("title", "checkout");
        return "checkout";
    }
}
