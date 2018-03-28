package com.company.validation;

import com.company.entity.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Kate M on 20.03.2018.
 */
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (!(product.getMultipartFile().getContentType().equals("image/jpeg") ||
                product.getMultipartFile().getContentType().equals("image/png") ||
                product.getMultipartFile().getContentType().equals("image/gif") ||
                product.getMultipartFile().getOriginalFilename().equals("") ||
                product.getMultipartFile() == null

        )) {
            errors.rejectValue("multipartFile", null, "Please, use only image file for upload!");
        }
    }
}
