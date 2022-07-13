package com.example.validation.web.dto;

import com.example.validation.validator.DtoLevelConstraint;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@DtoLevelConstraint
public class Dto {
  @NotNull(message = "Please fill in 'id' field")
  String id;

  String mode;

  @Size(max = 10)
  String code;

  @Valid
  EmbeddedDto embeddedM;
}
