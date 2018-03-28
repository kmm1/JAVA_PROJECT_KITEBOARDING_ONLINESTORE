package com.company.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Kate M on 18.03.2018.
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundError(Model model) {
        model.addAttribute("errorTitle", "The page is not constructed");
        model.addAttribute("errorDescription", "The page you are looking for is not available now!");
        model.addAttribute("title", "404 Error Page");
        return "error";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handlerProductNotFoundException(Model model) {
        model.addAttribute("errorTitle", "Product is not available");
        model.addAttribute("errorDescription", "The product you are looking for is not available now!");
        model.addAttribute("title", "Product Unavailable");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handlerException(Model model, Exception e) {
        model.addAttribute("errorTitle", "Contact your administrator");
        model.addAttribute("errorDescription", e.toString());
        model.addAttribute("title", "Error");
        return "error";
    }
}
