package com.car.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.car.model.Driver;
import com.car.service.DriverService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Register driver with file upload
    @PostMapping("/register")
    public ResponseEntity<String> registerDriver(@RequestParam("licenseNumber") String licenseNumber,
                                                 @RequestParam("licenseClass") String licenseClass,
                                                 @RequestParam("photo") MultipartFile photo) {
        try {
            driverService.registerDriver(licenseNumber, licenseClass, photo);
            return ResponseEntity.ok("Driver registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during registration: " + e.getMessage());
        }
    }

    // Register driver without file
    @PostMapping
    public ResponseEntity<Driver> saveDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.saveDriver(driver);
        return ResponseEntity.ok(savedDriver);
    }

    // Get all drivers
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }
}
