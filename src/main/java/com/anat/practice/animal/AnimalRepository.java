package com.anat.practice.animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

  @Query("SELECT a FROM Animal a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword,'%'))")
  List<Animal> findByNameContains(@Param("keyword") String keyword);

  @Query("SELECT a FROM Animal a WHERE a.type = :animalType")
  List<Animal> findByType(AnimalType animalType);
  
}
