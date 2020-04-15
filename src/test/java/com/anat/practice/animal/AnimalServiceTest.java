package com.anat.practice.animal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AnimalServiceTest {

  @Mock
  private AnimalRepository animalRepository;

  @InjectMocks
  private AnimalService animalService;

  @BeforeAll
  public static void setUpClass() {
    AnimalMockHelper.initial();
  }

  @Test
  public void testFindAllShouldCorrectly() {
    when(animalRepository.findAll()).thenReturn(AnimalMockHelper.findAll());

    List<Animal> animals = animalService.findAll();
    assertThat(animals.size(), is(equalTo(4)));
  }

  @Test
  public void testFindByTypeShouldCorrectly() {
    when(animalRepository.findByType(any(AnimalType.class))).then(i -> AnimalMockHelper.findByType(i.getArgument(0)));

    List<Animal> bugs = animalService.findByType(AnimalType.BUG);
    assertThat(bugs.size(), is(equalTo(2)));

    List<Animal> fishes = animalService.findByType(AnimalType.FISH);
    assertThat(fishes.size(), is(equalTo(2)));
  }
  
}
