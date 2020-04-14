package com.anat.practice.animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

  @Autowired
  private AnimalRepository animalRepository;

  public List<Animal> findByNameContains(String keyword) {
    return animalRepository.findByNameContains(keyword);
  }

  public List<Animal> findAll() {
    return animalRepository.findAll();
  }

  public List<Animal> findByType(AnimalType animalType) {
    return animalRepository.findByType(animalType);
  }

}
