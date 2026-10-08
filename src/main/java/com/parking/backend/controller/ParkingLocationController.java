package com.parking.backend.controller;

import com.parking.backend.entity.ParkingLocation;
import com.parking.backend.service.ParkingLocationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class ParkingLocationController {

    private final ParkingLocationService service;

    public ParkingLocationController(
            ParkingLocationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ParkingLocation>> getLocations() {
        return ResponseEntity.ok(
                service.getAllLocations());
    }

    @PostMapping
    public ResponseEntity<ParkingLocation> addLocation(
            @RequestBody ParkingLocation location) {

        return ResponseEntity.ok(
                service.addLocation(location));
    }
}