package com.netcracker.validator;

import com.netcracker.model.Itembodytype;
import com.netcracker.service.ItembodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ItembodytypeValidator implements Validator {
    private ItembodyService itembodyServiceImpl;

    @Autowired(required = true)
    @Qualifier(value = "itembodytypeService")
    public void setItembodyService(ItembodyService is) {
        this.itembodyServiceImpl = is;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Itembodytype.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Itembodytype itembodytype = (Itembodytype) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name is empty or contains a space");
        if (itembodytype.getName().length() > 50)
            errors.rejectValue("name", "", "Max length of name is 50");
        if (!itembodyServiceImpl.isNameUnique(itembodytype.getName()))
            errors.rejectValue("name", "", "Name is not unique");

    }
}
