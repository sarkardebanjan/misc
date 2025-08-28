package com.example.appwebservice.controller;

import com.example.appwebservice.model.Pet;
import com.example.appwebservice.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
@Tag(name = "Pet Management", description = "APIs for managing pets")
@SecurityRequirement(name = "oauth2")
public class PetController {

    @Autowired
    private PetService petService;

    @Operation(summary = "Get all pets", description = "Retrieve a list of all pets")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved pets"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Get pets with pagination", description = "Retrieve pets with pagination and sorting")
    @GetMapping("/paginated")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<Page<Pet>> getPetsPaginated(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort field") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<Pet> pets = petService.getAllPets(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Get pet by ID", description = "Retrieve a specific pet by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pet found"),
        @ApiResponse(responseCode = "404", description = "Pet not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<Pet> getPetById(@Parameter(description = "Pet ID") @PathVariable Long id) {
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new pet", description = "Add a new pet to the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pet created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_write')")
    public ResponseEntity<?> createPet(@Valid @RequestBody Pet pet) {
        try {
            Pet savedPet = petService.createPet(pet);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @Operation(summary = "Update an existing pet", description = "Update a pet's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pet updated successfully"),
        @ApiResponse(responseCode = "404", description = "Pet not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_write')")
    public ResponseEntity<?> updatePet(
            @Parameter(description = "Pet ID") @PathVariable Long id, 
            @Valid @RequestBody Pet petDetails) {
        try {
            Pet updatedPet = petService.updatePet(id, petDetails);
            return ResponseEntity.ok(updatedPet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a pet", description = "Remove a pet from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pet deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Pet not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_write')")
    public ResponseEntity<Void> deletePet(@Parameter(description = "Pet ID") @PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Search pets by type", description = "Find pets by their type (e.g., Dog, Cat)")
    @GetMapping("/type/{type}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> getPetsByType(
            @Parameter(description = "Pet type") @PathVariable String type) {
        List<Pet> pets = petService.getPetsByType(type);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Search pets by breed", description = "Find pets by their breed")
    @GetMapping("/breed/{breed}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> getPetsByBreed(
            @Parameter(description = "Pet breed") @PathVariable String breed) {
        List<Pet> pets = petService.getPetsByBreed(breed);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Search pets by owner", description = "Find pets by owner name")
    @GetMapping("/owner/{ownerName}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> getPetsByOwner(
            @Parameter(description = "Owner name") @PathVariable String ownerName) {
        List<Pet> pets = petService.getPetsByOwner(ownerName);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Search pets by age range", description = "Find pets within specified age range")
    @GetMapping("/age")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> getPetsByAgeRange(
            @Parameter(description = "Minimum age") @RequestParam Integer minAge,
            @Parameter(description = "Maximum age") @RequestParam Integer maxAge) {
        List<Pet> pets = petService.getPetsByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Search pets by name", description = "Find pets by partial name match")
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> searchPetsByName(
            @Parameter(description = "Pet name to search") @RequestParam String name) {
        List<Pet> pets = petService.searchPetsByName(name);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Get pets older than specified age", description = "Find pets older than given age")
    @GetMapping("/older-than/{age}")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<List<Pet>> getPetsOlderThan(
            @Parameter(description = "Age threshold") @PathVariable Integer age) {
        List<Pet> pets = petService.getPetsOlderThan(age);
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Get pet statistics", description = "Get statistics about pets")
    @GetMapping("/stats")
    @PreAuthorize("hasAuthority('SCOPE_read')")
    public ResponseEntity<Map<String, Object>> getPetStats() {
        long totalPets = petService.getTotalPetCount();
        long dogs = petService.getPetCountByType("Dog");
        long cats = petService.getPetCountByType("Cat");
        long birds = petService.getPetCountByType("Bird");

        Map<String, Object> stats = Map.of(
            "totalPets", totalPets,
            "dogs", dogs,
            "cats", cats,
            "birds", birds
        );

        return ResponseEntity.ok(stats);
    }
}