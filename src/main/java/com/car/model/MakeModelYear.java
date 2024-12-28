package com.car.model;

import jakarta.persistence.*;

@Entity
@Table(name = "make_model_year")
public class MakeModelYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary Key for MakeModelYear

    @OneToOne
    @MapsId
    @JoinColumn(name = "car_id")
    private Car car;  // One-to-one relationship with Car

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    public MakeModelYear(Car car, String make, String model, int year) {
        this.car = car;
        this.make = make;
        this.model = model;
        this.year = year;
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
