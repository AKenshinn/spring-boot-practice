package com.anat.practice.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals")
public class AnimalController {

  @Autowired
  private AnimalService animalService;

  @GetMapping("")
  public ResponseEntity<?> findAll() {
    return ResponseEntity.ok(animalService.findAll());
  }

  @GetMapping("/search")
  public ResponseEntity<?> findByNameContains(@RequestParam(value = "name") String name) {
    return ResponseEntity.ok(animalService.findByNameContains(name));
  }

}
