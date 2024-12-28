package com.car.service;

import com.car.model.Car;
import com.car.model.AvailableCar;
import com.car.model.MakeModelYear;
import com.car.model.User;
import com.car.repository.AvailableCarRepository;
import com.car.repository.CarRepository;
import com.car.repository.MakeModelYearRepository;
import com.car.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AvailableCarRepository availableCarRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MakeModelYearRepository makeModelYearRepository;
    public boolean isCarRegistrationNumberTaken(String registrationNumber) {
        return carRepository.findByRegistrationNumber(registrationNumber) != null;
    }

    // Method to register a new car
    public Car registerCar(Long userId, String registrationNumber, String make, int year, String model,
                           String category, int seatCapacity, MultipartFile carPhoto) throws IOException {
        // Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = new Car(registrationNumber, category, seatCapacity, user);
        // Set the user to the car (establish relationship)
        car.setUser(user);

        // Handle the car photo
        if (carPhoto != null && !carPhoto.isEmpty()) {
            // Generate a file path to store the image
            String photoPath = storePhoto(carPhoto);
            car.setPhotoPath(photoPath);  // Save the photo path to the car entity
        }

        // Save the car to the database (this will also cascade to related entities if needed)
        Car savedCar = carRepository.save(car);

        // Now, create and save the related entities (MakeModelYear and AvailableCar)
        // Create MakeModelYear entity
        MakeModelYear makeModelYear = new MakeModelYear(savedCar, make, model, year);
        makeModelYearRepository.save(makeModelYear); // Save MakeModelYear

        // Create AvailableCar entity and set availability to true (indicating it's available)
        AvailableCar availableCar = new AvailableCar(savedCar, true);
        availableCarRepository.save(availableCar); // Save AvailableCar

       // return savedCar;
        return savedCar;  // Return the saved car entity
    }
    public void bookCar(Long carId) {
        // Fetch the AvailableCar entity by car ID
        AvailableCar availableCar = availableCarRepository.findByCarId(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // If car is already unavailable, throw exception
        if (!availableCar.isAvailability()) {
            throw new RuntimeException("Car is already booked.");
        }

        // Set availability to false since the car is now booked
        availableCar.setAvailability(false);

        // Save the updated availability status
        availableCarRepository.save(availableCar);
    }

    // Method to return a car (set availability to true)
    public void returnCar(Long carId) {
        // Fetch the AvailableCar entity by car ID
        AvailableCar availableCar = availableCarRepository.findByCarId(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // Set availability to true since the car is now available for booking
        availableCar.setAvailability(true);

        // Save the updated availability status
        availableCarRepository.save(availableCar);
    }
    public List<Car> getAvailableCars() {
        // Fetch all AvailableCar entities where availability is true
        List<AvailableCar> availableCars = availableCarRepository.findAll().stream()
                .filter(AvailableCar::isAvailability)
                .collect(Collectors.toList());

        // Map the AvailableCar entities to Car entities
        return availableCars.stream()
                .map(AvailableCar::getCar)  // Assuming AvailableCar has a reference to the Car entity
                .collect(Collectors.toList());
    }
    // Helper method to store photo and return the file path
    private String storePhoto(MultipartFile photo) throws IOException {
        // Define a directory to store the photo
        String uploadDirectory = "uploads/cars/";

        // Ensure the directory exists
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }

        // Generate the file path
        Path path = Paths.get(uploadDirectory + photo.getOriginalFilename());

        // Copy the file to the target location
        Files.copy(photo.getInputStream(), path);

        // Return the relative path to the photo
        return path.toString();
    }
}
