package com.parking.backend.service;

import com.parking.backend.entity.ParkingLocation;
import com.parking.backend.repository.ParkingLocationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLocationService {

    private final ParkingLocationRepository repository;

    public ParkingLocationService(
            ParkingLocationRepository repository) {
        this.repository = repository;
    }

    public List<ParkingLocation> getAllLocations() {
        return repository.findAll();
    }

    public ParkingLocation addLocation(
            ParkingLocation location) {
        return repository.save(location);
    }
}