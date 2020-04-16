package com.anat.practice.animal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

  @Autowired
  private AnimalService animalService;

  @GetMapping("/animals")
  @ResponseStatus(code = HttpStatus.OK)
  public CollectionModel<Animal> findAll(
      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "200") Integer size,
      @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
      @RequestParam(value = "order", required = false, defaultValue = "ASC") Sort.Direction direction,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "type", required = false) AnimalType type) {

    // Create search criteria
    AnimalSearchCriteria criteria = AnimalSearchCriteria.builder()
        .page(page)
        .size(size)
        .sort(sort)
        .direction(direction)
        .name(name)
        .type(type)
        .build();

    // Get data and add each self link
    List<Animal> animals = animalService.findAll(criteria);
    animals.forEach(a -> a.add(linkTo(methodOn(AnimalController.class).findById(a.getId())).withSelfRel()));

    // Create self link of result list
    Link link = linkTo(methodOn(AnimalController.class).findAll(page, size, sort, direction, name, type))
        .withSelfRel();

    // Remove null parameter name from link
    link = link.expand();

    return new CollectionModel<>(animals, link);
  }

  @GetMapping("/animals/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Animal findById(@PathVariable(name = "id") Long id) {
    // Get data
    Animal animal = animalService.findById(id);

    // Add self link
    animal.add(linkTo(methodOn(AnimalController.class).findById(id)).withSelfRel());

    return animal;
  }

  @PostMapping("/animals")
  @ResponseStatus(code = HttpStatus.CREATED)
  public void save(@RequestBody Animal animal) {
    animalService.save(animal);
  }

  @PutMapping("/animals/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public void update(@PathVariable(name = "id") Long id, @RequestBody Animal animal) {
    animalService.update(id, animal);
  }

  @DeleteMapping("/animals/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable(name = "id") Long id) {
    animalService.delete(id);
  }

}
