package com.company.controller;

import com.company.entity.Category;
import com.company.entity.Product;
import com.company.exception.ProductNotFoundException;
import com.company.service.CategoryService;
import com.company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Kate M on 11.03.2018.
 */
@Controller
public class IndexController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public IndexController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @ModelAttribute("allCategories")
    public List<Category> addCategoryToModel() {
        return categoryService.findAll();
    }

    @GetMapping(path = {"/", "/home", "/index"})
    public String showHomePage(Model model) {
        ModelAndView modelAndView = new ModelAndView("/shared/sidebar");
        modelAndView.addObject("allCategories", categoryService.findAll());
        model.addAttribute("title", "Home");
        model.addAttribute("userClickHome", true);
        return "index";
    }

    @GetMapping(path = "/about")
    public String showAboutUs(Model model) {
        model.addAttribute("title", "About Us");
        model.addAttribute("userClickAbout", true);
        return "index";
    }

    @GetMapping(path = "/contact")
    public String showContact(Model model) {
        model.addAttribute("title", "Contact");
        model.addAttribute("userClickContact", true);
        return "index";
    }

    @GetMapping(path = "/show/all/products")
    public String showAllProducts(Model model) {
        model.addAttribute("userClickAllProducts", true);
        model.addAttribute("title", "ListProduct");
        return "index";
    }

    @GetMapping(path = "/show/category/{id}/products")
    public String showCategoryProducts(Model model, @PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category.getEnumCategory());
        model.addAttribute("categoryId", category.getId());
        model.addAttribute("title", "ListProduct");
        model.addAttribute("userClickCategoryProducts", true);
        return "index";
    }

    @GetMapping(path = "/show/{id}/product")
    public String showProductDescription(Model model, @PathVariable("id") Long id) throws ProductNotFoundException {
        Product product = productService.findById(id);
        model.addAttribute("title", product.getName());
        model.addAttribute("product", product);
        model.addAttribute("userClickShowProduct", true);
        return "index";
    }
}
