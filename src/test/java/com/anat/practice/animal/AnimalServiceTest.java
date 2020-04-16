package com.anat.practice.animal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AnimalServiceTest {

  @Autowired
  private AnimalService animalService;

  @Test
  public void testFindAllShouldCorrectly() {
    AnimalSearchCriteria criteria = AnimalSearchCriteria.builder()
      .page(1)
      .size(200)
      .direction(Sort.Direction.ASC)
      .sort("id")
      .build();

    List<Animal> animals = animalService.findAll(criteria);
    assertThat(animals.size(), is(equalTo(160)));
  }

  @Test
  public void testFindByNameContainsShouldCorrectly() {
    AnimalSearchCriteria criteria = AnimalSearchCriteria.builder()
      .page(1)
      .size(200)
      .direction(Sort.Direction.ASC)
      .sort("id")
      .name("common")
      .build();

    List<Animal> bugs = animalService.findAll(criteria);
    assertThat(bugs.size(), is(equalTo(2)));
  }

  @Test
  public void testFindByTypeShouldCorrectly() {
    AnimalSearchCriteria criteria = AnimalSearchCriteria.builder()
      .page(1)
      .size(200)
      .direction(Sort.Direction.ASC)
      .sort("id")
      .type(AnimalType.BUG)
      .build();

    List<Animal> bugs = animalService.findAll(criteria);
    assertThat(bugs.size(), is(equalTo(80)));
  }

}
