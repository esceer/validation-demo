package com.example.validation.web.dto;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class EmbeddedDto {
  @NotNull
  String name;
}
