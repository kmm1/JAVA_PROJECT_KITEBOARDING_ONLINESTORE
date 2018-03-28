package com.company.controller;

import com.company.FileUploadUtility;
import com.company.entity.Category;
import com.company.entity.Product;
import com.company.service.CategoryService;
import com.company.service.ProductService;
import com.company.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kate M on 18.03.2018.
 */
@Controller
@RequestMapping("/manage")
public class ManagementController {
    private static String UPLOAD_LOCATION = "C:\\Users\\Tom\\IdeaProjects\\store\\web\\src\\main\\webapp\\resources\\images\\";


    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ManagementController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @ModelAttribute("allCategories")
    public List<Category> addCategoryToModel() {
        return categoryService.findAll();
    }

    @GetMapping(path = "/products")
    public String showManageProducts(Model model, @RequestParam(name = "operation",
            required = false) String operation) {
        Product nProduct = new Product();
        Category nCategory = new Category();
        ModelAndView modelAndView = new ModelAndView("/manage-products");
        modelAndView.addObject("product", nProduct);

        model.addAttribute("userClickManageProduct", true);
        model.addAttribute("title", "Manage Products");
        model.addAttribute("product", nProduct);
        model.addAttribute("category", nCategory);

        if (operation != null) {
            if (operation.equals("product")) {
                model.addAttribute("message", "Product Submitted Successfully");
            }
            if (operation.equals("category")) {
                model.addAttribute("message", "Category Submitted Successfully");
            }
        }
        return "index";
    }

    @PostMapping(path = "/products")
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,
                                          BindingResult bindingResult, Model model,
                                          HttpServletRequest req) throws IOException {
        new ProductValidation().validate(mProduct, bindingResult);

        //@Valid BindingResult (is for validation)should be after @ModelAttribute
        if (bindingResult.hasErrors()) {
            model.addAttribute("userClickManageProduct", true);
            model.addAttribute("title", "Manage Products");
            model.addAttribute("message", "Validation failed for Product Submission");
            return "index";
        }
        MultipartFile multipartFile = mProduct.getMultipartFile();
        if (!multipartFile.getOriginalFilename().equals("")) {
            FileUploadUtility.uploadFile(req, multipartFile);
            mProduct.setImageURL(multipartFile.getOriginalFilename());
        } else {
            mProduct.setImageURL("no_image.PNG");
        }
        try {
            Long id = mProduct.getId();
            productService.findById(id);
            productService.update(mProduct);
        } catch (IllegalArgumentException e) {
            productService.save(mProduct);
        }
        return "redirect:/manage/products?operation=product";
    }

    @PostMapping(path = "/product/{id}/activation")
    @ResponseBody
    public String handleProductActivation(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        boolean availability = product.isAvailability();
        product.setAvailability(!availability);
        productService.update(product);
        return (product.isAvailability()) ? "The Product #" + product.getId() + " has been Activated"
                : "The Product #" + product.getId() + " has been Deactivated";
    }

    @GetMapping(path = "/{id}/product")
    public String manageExcistingProduct(Model model, @PathVariable(name = "id") Long id) {
        Product nProduct = productService.findById(id);
        model.addAttribute("userClickManageProduct", true);
        model.addAttribute("title", "Manage Products");
        model.addAttribute("product", nProduct);
        return "index";
    }

//        is not functional here because of enum Category
//    @PostMapping(path = "/category")
//    public String addNewCategory(@ModelAttribute(name = "enumCategory") String enumCategory) {
//        Category category = new Category();
//        System.out.println(EnumCategory.valueOf(enumCategory));
//        categoryService.save(category);
//        return "redirect:/manage/products?operation=category";
//    }

}
