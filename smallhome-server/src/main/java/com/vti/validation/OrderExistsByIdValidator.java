package com.vti.validation;


import com.vti.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class OrderExistsByIdValidator implements ConstraintValidator<OrderExistsById, Integer> {
    @Autowired
    private IOrderRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(id);
    }
}