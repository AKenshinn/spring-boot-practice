package com.anat.practice.animal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

  @Autowired
  private AnimalRepository animalRepository;

  public Animal findById(Long id) {
    return animalRepository.findById(id).get();
  }

  public List<Animal> findAll(AnimalSearchCriteria criteria) {
    PageRequest pageRequest = PageRequest.of(
        criteria.getPage() - 1,
        criteria.getSize(),
        Sort.by(criteria.getDirection(), criteria.getSort()));
    
    return animalRepository.findAll(getAnimalSpecification(criteria), pageRequest).getContent();
  }

  public void save(Animal animal) {
    animalRepository.save(animal);
  }

  public void update(Long id, Animal animal) {
    Animal animalToUpdate = animalRepository.getOne(id);
    animalToUpdate.setName(animal.getName());

    animalRepository.save(animalToUpdate);
  }

  public void delete(Long id) {
    animalRepository.deleteById(id);
  }

  /**
   * Create specification before query
   */
  private Specification<Animal> getAnimalSpecification(AnimalSearchCriteria criteria) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (!StringUtils.isEmpty(criteria.getName())) {
        predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
      }

      if (criteria.getType() != null) {
        predicates.add(cb.equal(root.get("type"), criteria.getType()));
      }

      return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }

}
