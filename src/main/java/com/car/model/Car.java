package com.car.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key for Car

    private String registrationNumber;
    private String category;
    private int seatCapacity;
    private String photoPath;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MakeModelYear makeModelYear;  // One-to-one relationship with MakeModelYear

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AvailableCar availableCar;  // One-to-one relationship with AvailableCar

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key column in Car table
    private User user;  // Many-to-one relationship with User

    // Constructor
    public Car(String registrationNumber, String category, int seatCapacity, User user) {
        this.registrationNumber = registrationNumber;
        this.category = category;
        this.seatCapacity = seatCapacity;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MakeModelYear getMakeModelYear() {
        return makeModelYear;
    }

    public void setMakeModelYear(MakeModelYear makeModelYear) {
        this.makeModelYear = makeModelYear;
    }

    public AvailableCar getAvailableCar() {
        return availableCar;
    }

    public void setAvailableCar(AvailableCar availableCar) {
        this.availableCar = availableCar;
    }
}
