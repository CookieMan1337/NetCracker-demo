package com.netcracker.validator;

import com.netcracker.model.ItemModel;
import com.netcracker.service.ItemmodelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ItemModelValidator implements Validator {
    private ItemmodelService itemmodelServiceImpl;

    @Autowired(required = true)
    @Qualifier(value = "itemmodelService")
    public void setItemmodelService(ItemmodelService is) {
        this.itemmodelServiceImpl = is;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemModel itemModel = (ItemModel) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Name is empty or contains a space");
        if (itemModel.getName().length() > 50)
            errors.rejectValue("length", "", "Max length of name is 50");
        if (!itemmodelServiceImpl.isNameUnique(itemModel.getName()))
            errors.rejectValue("name", "", "Name is not unique");

    }
}
