package com.netcracker.validator;

import com.netcracker.modelDTO.ItemDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemDTO item = (ItemDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name is empty or contains space");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "", "Color is empty or contains space");
        if (item.getPrice() < 0 || item.getPrice() > 10000000)
            errors.rejectValue("price", "", "Price must be between 0 and 10 billions");
        if (item.getName().length() > 50)
            errors.rejectValue("length", "", "Max length of name is 50 characters");
        if (item.getColor().length() > 50)
            errors.rejectValue("length", "", "Max length of name is 50 characters");
    }
}
