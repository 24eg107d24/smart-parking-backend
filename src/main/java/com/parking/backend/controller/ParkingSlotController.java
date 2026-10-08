package com.parking.backend.controller;

import com.parking.backend.entity.ParkingSlot;
import com.parking.backend.service.ParkingSlotService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin(origins = {
                "http://localhost:5173",
                "http://localhost:5174"
})
public class ParkingSlotController {

        private final ParkingSlotService parkingSlotService;

        public ParkingSlotController(
                        ParkingSlotService parkingSlotService) {

                this.parkingSlotService = parkingSlotService;
        }

        @GetMapping("/slots")
        public ResponseEntity<List<ParkingSlot>> getAllSlots() {

                return ResponseEntity.ok(
                                parkingSlotService.getAllSlots());
        }

        @GetMapping("/location/{locationId}")
        public ResponseEntity<List<ParkingSlot>> getSlotsByLocation(
                        @PathVariable Long locationId) {

                return ResponseEntity.ok(
                                parkingSlotService
                                                .getSlotsByLocation(locationId));
        }

        @GetMapping("/location/{locationId}/available")
        public ResponseEntity<List<ParkingSlot>> getAvailableSlotsByLocation(
                        @PathVariable Long locationId) {

                return ResponseEntity.ok(
                                parkingSlotService
                                                .getAvailableSlotsByLocation(
                                                                locationId));
        }

        @GetMapping("/available")
        public ResponseEntity<List<ParkingSlot>> getAvailableSlots() {

                return ResponseEntity.ok(
                                parkingSlotService
                                                .getAvailableSlots());
        }

        @GetMapping("/occupied")
        public ResponseEntity<List<ParkingSlot>> getOccupiedSlots() {

                return ResponseEntity.ok(
                                parkingSlotService
                                                .getOccupiedSlots());
        }

        @PostMapping("/slots")
        public ResponseEntity<ParkingSlot> addSlot(
                        @RequestBody ParkingSlot slot) {

                return ResponseEntity.ok(
                                parkingSlotService
                                                .addSlot(slot));
        }

        @PutMapping("/slots/{id}")
        public ResponseEntity<ParkingSlot> updateSlot(
                        @PathVariable Long id,
                        @RequestBody ParkingSlot slot) {

                return ResponseEntity.ok(
                                parkingSlotService
                                                .updateSlot(id, slot));
        }

        @DeleteMapping("/slots/{id}")
        public ResponseEntity<String> deleteSlot(
                        @PathVariable Long id) {

                parkingSlotService.deleteSlot(id);

                return ResponseEntity.ok(
                                "Parking slot deleted successfully");
        }
}