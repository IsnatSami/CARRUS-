package com.car.repository;

import com.car.model.AvailableCar;
import com.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailableCarRepository extends JpaRepository<AvailableCar, Long> {
    // No need to implement the save method, JpaRepository already provides it.
    Optional<AvailableCar> findByCarId(Long carId);}
