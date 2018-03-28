package com.company.validation;

import com.company.entity.User;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Kate M on 24.03.2018.
 */
public class RegistrationValidation implements Validator {

    private UserService userService;

    @Autowired
    public RegistrationValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (!user.getName().equals("")) {
            //should be == (not equals) because of nullPointerException
            if (userService.getUserByName(user.getName()).getName() != null) {
                if (userService.getUserByName(user.getName()).getName().equals(user.getName())) {
                    errors.rejectValue("name", null, "Name is already used");
                }
            }
        }
        if (!user.getEmail().equals("")) {
            //should be == (not equals) because of nullPointerException
            if (userService.getUserByEmail(user.getEmail()).getEmail() != null) {
                if (userService.getUserByEmail(user.getEmail()).getEmail().equals(user.getEmail())) {
                    errors.rejectValue("email", null, "Email is already used");
                }
            }
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", null, "Password does not match the confirm password");
        }


    }
}
