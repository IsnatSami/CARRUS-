package com.car.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.car.model.Driver;
import com.car.repository.DriverRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    // Directory for storing uploaded files
    @Value("${file.upload-dir}")
    private String uploadDir;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Save driver details along with file
    public Driver registerDriver(String licenseNumber, String licenseClass, MultipartFile photo) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file in the upload directory
        String photoFileName = photo.getOriginalFilename();
        if (photoFileName == null) {
            throw new IOException("File name is invalid.");
        }
        Path photoPath = uploadPath.resolve(photoFileName);
        Files.copy(photo.getInputStream(), photoPath);

        // Create and save the Driver entity
        Driver driver = new Driver();
        driver.setLicenseNumber(licenseNumber);
        driver.setLicenseClass(licenseClass);
        driver.setPhotoPath(photoPath.toString());

        return driverRepository.save(driver);
    }

    // Save driver without file
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    // Fetch all drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
}
