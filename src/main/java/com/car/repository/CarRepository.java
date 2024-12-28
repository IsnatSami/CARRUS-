package com.car.repository;

import com.car.model.Car;
import com.car.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    // You can add custom queries here if needed, e.g. find cars by user, etc.
    Car findByRegistrationNumber(String registrationNumber);
}
