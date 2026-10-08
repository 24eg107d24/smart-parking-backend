package com.parking.backend.repository;

import com.parking.backend.entity.ParkingLocation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLocationRepository
        extends JpaRepository<ParkingLocation, Long> {

}