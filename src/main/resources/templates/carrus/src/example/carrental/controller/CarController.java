package com.yourcompany.carrental.controller;

//package com.example.car;

import com.yourcompany.carrental.model.Car;
import com.yourcompany.carrental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;
    private String model;

    @PostMapping("/register")
    public String registerCar(@RequestParam("registration-number") String registrationNumber,
                              @RequestParam("make-year") String makeYear,
                              @RequestParam("model") String model,
                              @RequestParam("category") String category,
                              @RequestParam("seat-capacity") int seatCapacity,
                              @RequestParam("car-photo") MultipartFile carPhoto,
                              Model ignoredModel) {
        this.model = model;
        try {
            Car car = carService.registerCar(registrationNumber, makeYear, model, category, seatCapacity, carPhoto);
         //   model.addAttribute("message", "Car registered successfully with ID: " + car.getId());
            return "car_registration_success";  // Return to a success page
        } catch (IOException e) {
          //  model.addAttribute("message", "Failed to upload car photo.");
            return "car_registration_failure";  // Return to a failure page
        }
    }
}
