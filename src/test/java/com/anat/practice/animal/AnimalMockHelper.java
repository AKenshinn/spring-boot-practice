package com.anat.practice.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalMockHelper {

  private static List<Animal> animals = new ArrayList<>();

  public static List<Animal> findAll() {
    return animals;
  }

  public static List<Animal> findByType(AnimalType type) {
    return animals.stream()
        .filter(a -> a.getType().equals(type))
        .collect(Collectors.toList());
  }

  public static List<Animal> findByNameContains(String keyword) {
    return animals.stream()
        .filter(a -> a.getName().toLowerCase().contains(keyword))
        .collect(Collectors.toList());
  }

  public static void initial() {
    if (!animals.isEmpty()) {
      return;
    }

    Animal bug1 = Animal.builder()
        .name("Bug1")
        .type(AnimalType.BUG)
        .build();

    Animal bug2 = Animal.builder()
        .name("Bug2")
        .type(AnimalType.BUG)
        .build();

    Animal fish1 = Animal.builder()
        .name("Fish1")
        .type(AnimalType.FISH)
        .build();

    Animal fish2 = Animal.builder()
        .name("Fish2")
        .type(AnimalType.FISH)
        .build();

    animals.add(bug1);
    animals.add(bug2);
    animals.add(fish1);
    animals.add(fish2);
  }

}
