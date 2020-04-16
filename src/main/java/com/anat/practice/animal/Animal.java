package com.anat.practice.animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="animals")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Animal extends RepresentationModel<Animal> {

  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

  private String name;

  private AnimalType type;

  private String location;

  private String shadowSize;

  private int value;

  private String time;

  private String month;

  public Animal(String name, AnimalType type, String location, String shadowSize, int value, String time, String month) {
    this.name = name;
    this.type = type;
    this.location = location;
    this.shadowSize = shadowSize;
    this.value = value;
    this.time = time;
    this.month = month;
  }
  
}
