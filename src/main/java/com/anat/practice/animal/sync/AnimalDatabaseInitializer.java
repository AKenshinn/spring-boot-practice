package com.anat.practice.animal.sync;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("animalDatabaseInitializer")
public class AnimalDatabaseInitializer implements InitializingBean {

  @Autowired
  private AnimalDataSynchronizer animalDataSynchronizer;

  @Override
  public void afterPropertiesSet() throws Exception {
    animalDataSynchronizer.initial();
  }

}
