package com.example.appwebservice.config;

import com.example.appwebservice.model.Pet;
import com.example.appwebservice.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PetRepository petRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (petRepository.count() == 0) {
            // Create sample pets
            Pet pet1 = new Pet("Buddy", "Dog", "Golden Retriever", 3, "Golden", "John Doe");
            Pet pet2 = new Pet("Whiskers", "Cat", "Persian", 5, "White", "Jane Smith");
            Pet pet3 = new Pet("Charlie", "Dog", "Labrador", 2, "Black", "Bob Johnson");
            Pet pet4 = new Pet("Luna", "Cat", "Siamese", 4, "Cream", "Alice Brown");
            Pet pet5 = new Pet("Max", "Dog", "German Shepherd", 6, "Brown", "Mike Wilson");
            Pet pet6 = new Pet("Milo", "Cat", "Maine Coon", 3, "Gray", "Sarah Davis");
            Pet pet7 = new Pet("Tweety", "Bird", "Canary", 2, "Yellow", "Tom Anderson");
            Pet pet8 = new Pet("Rio", "Bird", "Parrot", 4, "Green", "Lisa Garcia");

            petRepository.save(pet1);
            petRepository.save(pet2);
            petRepository.save(pet3);
            petRepository.save(pet4);
            petRepository.save(pet5);
            petRepository.save(pet6);
            petRepository.save(pet7);
            petRepository.save(pet8);

            System.out.println("Sample pet data initialized successfully!");
        }
    }
}