package com.netcracker.validator;

import com.netcracker.model.Storage;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StorageValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Storage.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Storage storage = (Storage) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "Address is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Name is empty");
        if (storage.getCapacity() <= 0)
            errors.rejectValue("capacity", "", "Capacity can't be negative");
        if (storage.getName().length() > 50)
            errors.rejectValue("name", "", "Max length of name is 50");
        if (storage.getAddress().length() > 50)
            errors.rejectValue("address", "", "Max length of address is 50");

    }
}
