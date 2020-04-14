package com.anat.practice.animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {

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

  protected Animal() { }

  public Animal(String name, AnimalType type, String location, String shadowSize, int value, String time, String month) {
    this.name = name;
    this.type = type;
    this.location = location;
    this.shadowSize = shadowSize;
    this.value = value;
    this.time = time;
    this.month = month;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AnimalType getType() {
    return type;
  }

  public void setType(AnimalType type) {
    this.type = type;
  }

  public String getLocation() {
    return location;
  }

  public String getShadowSize() {
    return shadowSize;
  }

  public void setShadowSize(String shadowSize) {
    this.shadowSize = shadowSize;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

}
