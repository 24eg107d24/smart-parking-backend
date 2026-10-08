package com.parking.backend.service;

import com.parking.backend.entity.ParkingSlot;
import com.parking.backend.repository.ParkingSlotRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotService(
            ParkingSlotRepository parkingSlotRepository) {

        this.parkingSlotRepository = parkingSlotRepository;
    }

    public List<ParkingSlot> getAllSlots() {

        return parkingSlotRepository.findAll();
    }

    public List<ParkingSlot> getAvailableSlots() {

        return parkingSlotRepository
                .findByOccupied(false);
    }

    public List<ParkingSlot> getOccupiedSlots() {

        return parkingSlotRepository
                .findByOccupied(true);
    }

    public List<ParkingSlot> getSlotsByLocation(
            Long locationId) {

        return parkingSlotRepository
                .findByLocationId(locationId);
    }

    public List<ParkingSlot> getAvailableSlotsByLocation(
            Long locationId) {

        return parkingSlotRepository
                .findByLocationIdAndOccupied(
                        locationId,
                        false);
    }

    public ParkingSlot addSlot(
            ParkingSlot slot) {

        return parkingSlotRepository.save(slot);
    }

    public ParkingSlot updateSlot(
            Long id,
            ParkingSlot updatedSlot) {

        ParkingSlot slot = parkingSlotRepository
                .findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Parking slot not found"));

        slot.setSlotNumber(
                updatedSlot.getSlotNumber());

        slot.setVehicleType(
                updatedSlot.getVehicleType());

        slot.setOccupied(
                updatedSlot.isOccupied());

        slot.setLocation(
                updatedSlot.getLocation());

        return parkingSlotRepository.save(slot);
    }

    public void deleteSlot(Long id) {

        if (!parkingSlotRepository.existsById(id)) {

            throw new RuntimeException(
                    "Parking slot not found");
        }

        parkingSlotRepository.deleteById(id);
    }
}