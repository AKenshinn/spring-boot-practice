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

    Animal bug1 = new Animal();
    bug1.setName("Bug1");
    bug1.setType(AnimalType.BUG);

    Animal bug2 = new Animal();
    bug2.setName("Bug2");
    bug2.setType(AnimalType.BUG);

    Animal fish1 = new Animal();
    fish1.setName("Fish1");
    fish1.setType(AnimalType.FISH);

    Animal fish2 = new Animal();
    fish2.setName("Fish2");
    fish2.setType(AnimalType.FISH);

    animals.add(bug1);
    animals.add(bug2);
    animals.add(fish1);
    animals.add(fish2);
  }

}
