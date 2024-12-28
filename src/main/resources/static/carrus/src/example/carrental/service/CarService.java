package com.yourcompany.carrental.service;

import com.yourcompany.carrental.model.Car;
import com.yourcompany.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

///package com.example.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CarService {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private CarRepository carRepository;

    public Car registerCar(String registrationNumber, String makeYear, String model, String category, int seatCapacity, MultipartFile carPhoto) throws IOException {

        // Create the upload directory if it doesn't exist
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Save the photo
        String photoPath = UPLOAD_DIR + carPhoto.getOriginalFilename();
        File photoFile = new File(photoPath);
        carPhoto.transferTo(photoFile);

        // Save car details in the database
        Car car = new Car();
        car.setRegistrationNumber(registrationNumber);
        car.setMakeYear(makeYear);
        car.setModel(model);
        car.setCategory(category);
        car.setSeatCapacity(seatCapacity);
        car.setCarPhoto(photoPath);

        return carRepository.save(car);  // Save the car details to the database
    }
}
