package com.parking.backend.controller;

import com.parking.backend.entity.Booking;
import com.parking.backend.service.BookingService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long slotId,
            @RequestParam LocalDateTime startTime,
            @RequestParam int durationHours,
            Authentication authentication) {

        Long userId = (Long) authentication.getDetails();

        return ResponseEntity.ok(
                bookingService.createBooking(
                        userId,
                        slotId,
                        startTime,
                        durationHours));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Booking>> getMyBookings(
            Authentication authentication) {

        Long userId = (Long) authentication.getDetails();

        return ResponseEntity.ok(
                bookingService.getUserBookings(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                bookingService.getUserBookings(userId));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {

        return ResponseEntity.ok(
                bookingService.getAllBookings());
    }
}