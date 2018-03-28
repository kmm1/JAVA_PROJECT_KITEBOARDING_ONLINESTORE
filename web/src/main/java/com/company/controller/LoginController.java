package com.company.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kate M on 25.03.2018.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model,
                                @RequestParam(name = "error", required = false) String error,
                                @RequestParam(name = "logout", required = false) String logout) {
        if (error != null) {
            if (error.equals("true")) {
                model.addAttribute("message", "Please, enter valid Name and Password");
                model.addAttribute("messageForName", "Please, enter valid Name");
                model.addAttribute("messageForPassword", "Please, enter valid Password");
            }
        }
        if (logout != null) {
            model.addAttribute("message", "User has successfully logout");

        }
        model.addAttribute("title", "Login");
        return "login";
    }

    @GetMapping("error")
    public String showErrorPage(Model model) {
        model.addAttribute("errorTitle", "403");
        model.addAttribute("errorDescription", "Access denied");
        model.addAttribute("title", "403");
        return "error";
    }

    @GetMapping("/perform-logout")
    public String logout(HttpServletRequest req, HttpServletResponse res) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(req, res, authentication);
        }
        return "redirect:login?logout";
    }
}
