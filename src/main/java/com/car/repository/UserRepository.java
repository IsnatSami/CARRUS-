package com.car.repository;

import com.car.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Find user by email and password (make sure to hash passwords in real applications)
    User findByEmailAndPassword(String email, String password);

    // Check if email already exists (useful during sign-up)
    User findByEmail(String email);
}
