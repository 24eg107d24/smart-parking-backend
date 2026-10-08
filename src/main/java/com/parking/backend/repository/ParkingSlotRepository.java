package com.parking.backend.repository;

import com.parking.backend.entity.ParkingSlot;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository
        extends JpaRepository<ParkingSlot, Long> {

    List<ParkingSlot> findByOccupied(boolean occupied);

    List<ParkingSlot> findByLocationId(Long locationId);

    List<ParkingSlot> findByLocationIdAndOccupied(
            Long locationId,
            boolean occupied);
}