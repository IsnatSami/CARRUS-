package com.car.controller;

import com.car.model.Car;
import com.car.model.MakeModelYear;
import com.car.model.AvailableCar;
import com.car.model.User;
import com.car.repository.UserRepository;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api") // API URL prefix
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/cars/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        List<Car> availableCars = carService.getAvailableCars();
        return ResponseEntity.ok(availableCars);
    }
    // Handle car registration requests
    @PostMapping("/cars/register")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> registerCar(
            @RequestHeader("logged-in-user-id") Long userId,
            @RequestParam("registration-number") String registrationNumber,
            @RequestParam("make") String make,
            @RequestParam("model") String model,
            @RequestParam("year") int year,
            @RequestParam("category") String category,
            @RequestParam("seat-capacity") int seatCapacity,
            @RequestParam("car-photo-upload") MultipartFile carPhoto) {

        // Check if the car with the same registration number already exists
        if (carService.isCarRegistrationNumberTaken(registrationNumber)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Car with this registration number already exists");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
      //  User user = userOptional.get();

        try {
            Car car = carService.registerCar(userId, registrationNumber, make, year, model, category, seatCapacity, carPhoto);

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Car registration successful");
            response.put("carId", car.getId()); // Return car ID as a reference
            response.put("registrationNumber", car.getRegistrationNumber());
            response.put("userId", userId);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Handle unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during car registration: " + e.getMessage());
        }
    }
    @PostMapping("/cars/book/{carId}")
    public ResponseEntity<?> bookCar(@PathVariable Long carId) {
        try {
            carService.bookCar(carId);
            return ResponseEntity.ok("Car booked successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to return a car
    @PostMapping("/cars/return/{carId}")
    public ResponseEntity<?> returnCar(@PathVariable Long carId) {
        try {
            carService.returnCar(carId);
            return ResponseEntity.ok("Car returned successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
