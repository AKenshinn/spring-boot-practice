package com.anat.practice.animal;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AnimalServiceTest {

  @Autowired
  private AnimalService animalService;

  @Test
  public void testFindAllShouldCorrectly() {
    List<Animal> animals = animalService.findAll();
    assertThat(animals.size(), Matchers.equalTo(160));
  }

  @Test
  public void testFindByTypeShouldCorrectly() {
    List<Animal> bugs = animalService.findByType(AnimalType.BUG);
    assertThat(bugs.size(), Matchers.equalTo(80));

    List<Animal> fishes = animalService.findByType(AnimalType.FISH);
    assertThat(fishes.size(), Matchers.equalTo(80));
  }
  
}
