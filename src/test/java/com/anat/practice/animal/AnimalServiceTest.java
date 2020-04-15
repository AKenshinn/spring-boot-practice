package com.anat.practice.animal;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {

  @Mock
  private AnimalRepository animalRepository;

  @InjectMocks
  private AnimalService animalService;

  @BeforeClass
  public static void setUpClass() {
    AnimalMockHelper.initial();
  }

  @Test
  public void testFindAllShouldCorrectly() {
    when(animalRepository.findAll()).thenReturn(AnimalMockHelper.findAll());

    List<Animal> animals = animalService.findAll();
    assertThat(animals.size(), Matchers.equalTo(4));
  }

  @Test
  public void testFindByTypeShouldCorrectly() {
    when(animalRepository.findByType(any(AnimalType.class))).then(i -> AnimalMockHelper.findByType(i.getArgument(0)));

    List<Animal> bugs = animalService.findByType(AnimalType.BUG);
    assertThat(bugs.size(), Matchers.equalTo(2));

    List<Animal> fishes = animalService.findByType(AnimalType.FISH);
    assertThat(fishes.size(), Matchers.equalTo(2));
  }
  
}
