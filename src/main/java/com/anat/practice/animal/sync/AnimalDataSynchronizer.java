package com.anat.practice.animal.sync;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.anat.practice.animal.Animal;
import com.anat.practice.animal.AnimalRepository;
import com.anat.practice.animal.AnimalType;
import com.anat.practice.util.csv.CsvReaderUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class AnimalDataSynchronizer {

  private static final Logger LOGGER = LoggerFactory.getLogger(AnimalDataSynchronizer.class);
  private static final String BUG_DATA_PATH = "data/bug.csv";
  private static final String FISH_DATA_PATH = "data/fish.csv";

  @Autowired
  private AnimalRepository animalRepository;

  @Autowired
  private CsvReaderUtils csvReaderUtils;

  public void initial() throws Exception {
    LOGGER.info("Initial animal data");

    initialAnimals(BUG_DATA_PATH, AnimalType.BUG);
    initialAnimals(FISH_DATA_PATH, AnimalType.FISH);
  }
  
  @Transactional
  private void initialAnimals(String animalDataPath, AnimalType animalType) throws Exception {
    LOGGER.info("Initial animal data. : " +  animalType);
    try {
      File animalDataFile = ResourceUtils.getFile("classpath:" + animalDataPath);
      List<List<String>> animalData= csvReaderUtils.read(animalDataFile);
      List<Animal> animals = animalData.stream()
          .map(b -> new Animal(b.get(0), animalType, b.get(1), b.get(2), Integer.parseInt(b.get(3).replace(",","")), b.get(4), b.get(5)))
          .collect(Collectors.toList());

      animalRepository.saveAll(animals);

      LOGGER.info("Initial animal data success. :" + animalType);
    } catch (Exception e) {
      LOGGER.error("Initial animal data failure. :" + animalType, e);
    }
  }
  
}