package com.example.appwebservice.service;

import com.example.appwebservice.model.Pet;
import com.example.appwebservice.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    /**
     * Get all pets
     */
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    /**
     * Get all pets with pagination and sorting
     */
    public Page<Pet> getAllPets(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? 
            Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return petRepository.findAll(pageable);
    }

    /**
     * Get pet by ID
     */
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    /**
     * Create a new pet
     */
    public Pet createPet(Pet pet) {
        // Check if pet with same name and owner already exists
        if (petRepository.existsByNameAndOwnerName(pet.getName(), pet.getOwnerName())) {
            throw new IllegalArgumentException(
                "A pet with name '" + pet.getName() + 
                "' already exists for owner '" + pet.getOwnerName() + "'");
        }
        return petRepository.save(pet);
    }

    /**
     * Update an existing pet
     */
    public Pet updatePet(Long id, Pet petDetails) {
        return petRepository.findById(id).map(pet -> {
            pet.setName(petDetails.getName());
            pet.setType(petDetails.getType());
            pet.setBreed(petDetails.getBreed());
            pet.setAge(petDetails.getAge());
            pet.setColor(petDetails.getColor());
            pet.setOwnerName(petDetails.getOwnerName());
            return petRepository.save(pet);
        }).orElseThrow(() -> new RuntimeException("Pet not found with id " + id));
    }

    /**
     * Delete a pet by ID
     */
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found with id " + id);
        }
        petRepository.deleteById(id);
    }

    /**
     * Find pets by type
     */
    public List<Pet> getPetsByType(String type) {
        return petRepository.findByTypeIgnoreCase(type);
    }

    /**
     * Find pets by breed
     */
    public List<Pet> getPetsByBreed(String breed) {
        return petRepository.findByBreedIgnoreCase(breed);
    }

    /**
     * Find pets by owner name
     */
    public List<Pet> getPetsByOwner(String ownerName) {
        return petRepository.findByOwnerNameIgnoreCase(ownerName);
    }

    /**
     * Find pets by age range
     */
    public List<Pet> getPetsByAgeRange(Integer minAge, Integer maxAge) {
        return petRepository.findByAgeBetween(minAge, maxAge);
    }

    /**
     * Find pets by name (partial match)
     */
    public List<Pet> searchPetsByName(String name) {
        return petRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Find pets older than specified age
     */
    public List<Pet> getPetsOlderThan(Integer age) {
        return petRepository.findPetsOlderThan(age);
    }

    /**
     * Find pets by type and breed
     */
    public List<Pet> getPetsByTypeAndBreed(String type, String breed) {
        return petRepository.findByTypeAndBreed(type, breed);
    }

    /**
     * Get count of pets by type
     */
    public long getPetCountByType(String type) {
        return petRepository.countByType(type);
    }

    /**
     * Get total count of pets
     */
    public long getTotalPetCount() {
        return petRepository.count();
    }
}