package com.example.appwebservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Pet name is required")
    @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Pet type is required")
    @Size(min = 2, max = 30, message = "Pet type must be between 2 and 30 characters")
    @Column(nullable = false)
    private String type;

    @NotBlank(message = "Pet breed is required")
    @Size(min = 2, max = 50, message = "Pet breed must be between 2 and 50 characters")
    @Column(nullable = false)
    private String breed;

    @NotNull(message = "Pet age is required")
    @Column(nullable = false)
    private Integer age;

    @Size(max = 20, message = "Pet color must be less than 20 characters")
    private String color;

    @Size(max = 50, message = "Owner name must be less than 50 characters")
    private String ownerName;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
    }

    // Default constructor
    public Pet() {}

    // Constructor with parameters
    public Pet(String name, String type, String breed, Integer age, String color, String ownerName) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.ownerName = ownerName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}