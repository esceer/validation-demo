package com.example.validation.web;

import com.example.validation.web.dto.Dto;
import com.example.validation.web.dto.EmbeddedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class Controller {

  @GetMapping
  public ResponseEntity<Dto> getDto() {
    var dto = new Dto("id", "enabled", "fitting", new EmbeddedDto("name"));
    return ResponseEntity.ok(dto);
  }

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<String> addDto(@RequestBody @Valid Dto dto) {
    System.out.println("Adding dto...");
    System.out.println(dto);
    return ResponseEntity.ok("ok");
  }
}
