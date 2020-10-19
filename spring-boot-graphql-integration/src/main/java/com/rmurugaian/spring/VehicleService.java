package com.rmurugaian.spring;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    @Transactional
    Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate);

    @Transactional(readOnly = true)
    List<Vehicle> getAllVehicles(int count);

    @Transactional(readOnly = true)
    Optional<Vehicle> getVehicle(int id);
}
