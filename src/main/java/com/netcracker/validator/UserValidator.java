package com.netcracker.validator;

import com.netcracker.model.User;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (!userService.isUserLoginUnique(user.getId(), user.getLogin())) {
            errors.rejectValue("login", "", "This login has used already.");
        }
        if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
            errors.rejectValue("email", "", "This email has used already.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "", "Login is empty or contains a space");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "", "First name is empty or contains a space");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "", "Last name is empty or contains a space");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is empty or contains a space");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is empty or contains a space");
        if (user.getLogin().length() > 20)
            errors.rejectValue("login", "", "Login's length must be less 20 chars");
        if (user.getFirstname().length() > 20)
            errors.rejectValue("firstname", "", "First name must be less 20 chars");
        if (user.getLastname().length() > 20)
            errors.rejectValue("lastname", "", "Last name length must be less 20 chars");
        if (user.getPassword().length() > 20 || user.getPassword().length() < 8)
            errors.rejectValue("password", "", "Password's length must be 8-20 chars");
        if (user.getEmail().length() > 50)
            errors.rejectValue("email", "", "Email's length must be less 50 chars");

    }
}
