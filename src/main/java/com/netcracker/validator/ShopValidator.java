package com.netcracker.validator;

import com.netcracker.modelDTO.ShopDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> aClass) {
        return ShopDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final String phonePattern = "\\+7\\([0-9]{3}\\)[0-9]{3}\\-[0-9]{2}\\-[0-9]{2}";

        pattern = Pattern.compile(phonePattern);

        ShopDTO shop = (ShopDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name is empty or contains space");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "Address is empty or contains space");

        if (shop.getStorages().size() == 0)
            errors.rejectValue("storages", "", "Choose at least 1 option");
        if (shop.getName().length() < 3 || shop.getName().length() > 50)
            errors.rejectValue("name", "", "Length must be between 3 and 50 chars");
        if (shop.getAddress().length() < 3 || shop.getAddress().length() > 50)
            errors.rejectValue("address", "", "Length must be between 3 and 50 chars");
        matcher = pattern.matcher(shop.getPhone());
        if (!matcher.matches())
            errors.rejectValue("phone", "", "Please enter valid phone number like +7(920)250-11-11");

    }
}
