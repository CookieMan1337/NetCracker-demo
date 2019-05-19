package com.netcracker.validator;

import com.netcracker.modelDTO.AvailableDTO;
import com.netcracker.service.AvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AvailableValidator implements Validator {
    AvailableService availableService;

    @Autowired
    @Qualifier(value = "availableService")
    public void setAvailableService(AvailableService availableService) {
        this.availableService = availableService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return AvailableDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AvailableDTO available = (AvailableDTO) o;

        if (available.getAction().equals("add") && availableService.findByNumId(available.getItem_id(), available.getStorage_id()) != null) {
            errors.rejectValue("storage_id", "", "You try to add an existing available");
        }
        if (available.getQuantity() < 1 || available.getQuantity() > 1000000) {
            errors.rejectValue("quantity", "", "Quantity must be between 1 and 1 billion");
        }
        if (available.getDeliverytime() < 0 || available.getDeliverytime() > 10000) {
            errors.rejectValue("deliverytime", "", "Delivery must be between 0 and 10000 days");
        }
    }
}
