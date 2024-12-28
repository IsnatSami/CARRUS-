package com.yourcompany.carrental.repository;



import com.yourcompany.carrental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    // Add custom queries if needed
}
