package com.anat.practice.animal;

import org.springframework.data.domain.Sort;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AnimalSearchCriteria {

  private Integer page;

  private Integer size;

  private Sort.Direction direction;

  private String sort;
  
  private String name;

  private AnimalType type;

}
