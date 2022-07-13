package com.example.validation.validator;

import com.example.validation.web.dto.Dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidator implements ConstraintValidator<DtoLevelConstraint, Dto> {

  @Override
  public void initialize(DtoLevelConstraint constraint) {
  }

  @Override
  public boolean isValid(Dto dto, ConstraintValidatorContext cxt) {
    return "enabled".equals(dto.getMode()) && dto.getCode().length() > 1;
  }
}