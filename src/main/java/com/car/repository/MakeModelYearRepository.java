package com.car.repository;

import com.car.model.MakeModelYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeModelYearRepository extends JpaRepository<MakeModelYear, Long> {
    // No need to implement the save method, JpaRepository already provides it.
}
