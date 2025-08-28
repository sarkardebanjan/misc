package com.example.appwebservice.repository;

import com.example.appwebservice.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Find pets by type (e.g., Dog, Cat, Bird)
    List<Pet> findByTypeIgnoreCase(String type);

    // Find pets by breed
    List<Pet> findByBreedIgnoreCase(String breed);

    // Find pets by owner name
    List<Pet> findByOwnerNameIgnoreCase(String ownerName);

    // Find pets by age range
    List<Pet> findByAgeBetween(Integer minAge, Integer maxAge);

    // Find pets by name containing (partial match)
    List<Pet> findByNameContainingIgnoreCase(String name);

    // Custom query to find pets older than specified age
    @Query("SELECT p FROM Pet p WHERE p.age > :age")
    List<Pet> findPetsOlderThan(@Param("age") Integer age);

    // Custom query to find pets by type and breed
    @Query("SELECT p FROM Pet p WHERE p.type = :type AND p.breed = :breed")
    List<Pet> findByTypeAndBreed(@Param("type") String type, @Param("breed") String breed);

    // Count pets by type
    long countByType(String type);

    // Check if pet exists by name and owner
    boolean existsByNameAndOwnerName(String name, String ownerName);
}