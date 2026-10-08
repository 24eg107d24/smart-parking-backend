package com.parking.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_slots")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotNumber;

    private String vehicleType;

    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private ParkingLocation location;

    public ParkingSlot() {
    }

    public ParkingSlot(
            String slotNumber,
            String vehicleType,
            boolean occupied,
            ParkingLocation location) {

        this.slotNumber = slotNumber;
        this.vehicleType = vehicleType;
        this.occupied = occupied;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public ParkingLocation getLocation() {
        return location;
    }

    public void setLocation(ParkingLocation location) {
        this.location = location;
    }
}