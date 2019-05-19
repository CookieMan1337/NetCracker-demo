package com.netcracker.validator;

import com.netcracker.model.User;
import com.netcracker.modelDTO.ProfileDTO;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProfileValidator implements Validator {
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier(value = "passwordEncoder")
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ProfileDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProfileDTO profileDTO = (ProfileDTO) o;
        User user = userService.findById(profileDTO.getId());
        if (user == null)
            errors.rejectValue("id", "", "User if not found");
        else {
            if (profileDTO.getAction().equals("info")) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "", "First name must be not empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "", "Last name must be not empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email must be not empty");
                if (profileDTO.getFirstname().length() > 20)
                    errors.rejectValue("firstname", "", "First name must be less 20 chars");
                if (profileDTO.getLastname().length() > 20)
                    errors.rejectValue("lastname", "", "Last name length must be less 20 chars");
                if (!user.getEmail().equals(profileDTO.getEmail()) && !userService.isUserEmailUnique(user.getId(), profileDTO.getEmail())) {
                    errors.rejectValue("email", "", "This email is used already");
                }
                if (user.getEmail().length() > 50)
                    errors.rejectValue("email", "", "Email's length must be less 50 chars");

            } else if (profileDTO.getAction().equals("pass")) {
//                if (!profileDTO.getPassword().equals(user.getPassword()))
                if (!passwordEncoder.matches(profileDTO.getOldPassword(), user.getPassword()))
                    errors.rejectValue("oldPassword", "", "Old password isn't match with current password");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword", "", "Old password must be not empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password must be not empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "", "Confirm password must be not empty");
                if (!profileDTO.getPassword().equals(profileDTO.getPasswordConfirm()))
                    errors.rejectValue("passwordConfirm", "", "Passwords are not match");
                if (profileDTO.getPassword().length() > 20 || profileDTO.getPassword().length() < 8)
                    errors.rejectValue("password", "", "Password's length must be 8-20 chars");
                if (profileDTO.getPasswordConfirm().length() > 20 || profileDTO.getPasswordConfirm().length() < 8)
                    errors.rejectValue("passwordConfirm", "", "Password's length must be 8-20 chars");


            }
        }
    }
}
