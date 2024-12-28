package com.car.model;

import jakarta.persistence.*;

@Entity
@Table(name = "available_car")
public class AvailableCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key for AvailableCar

    @OneToOne
    @MapsId
    @JoinColumn(name = "car_id")
    private Car car;  // One-to-one relationship with Car

    @Column(nullable = false)
    private boolean availability;
    public AvailableCar(Car car, boolean availability) {
        this.car = car;
        this.availability = availability;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
