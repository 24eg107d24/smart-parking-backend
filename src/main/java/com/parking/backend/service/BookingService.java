package com.parking.backend.service;

import com.parking.backend.entity.Booking;
import com.parking.backend.entity.ParkingSlot;
import com.parking.backend.repository.BookingRepository;
import com.parking.backend.repository.ParkingSlotRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    public BookingService(
            BookingRepository bookingRepository,
            ParkingSlotRepository parkingSlotRepository) {

        this.bookingRepository = bookingRepository;
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public Booking createBooking(
            Long userId,
            Long slotId,
            LocalDateTime startTime,
            int durationHours) {

        ParkingSlot slot = parkingSlotRepository
                .findById(slotId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Parking slot not found"));

        if (durationHours <= 0) {
            throw new RuntimeException(
                    "Parking duration must be greater than zero");
        }

        LocalDateTime endTime = startTime.plusHours(durationHours);

        boolean alreadyBooked = bookingRepository
                .existsBySlotIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        slotId,
                        endTime,
                        startTime);

        if (alreadyBooked) {
            throw new RuntimeException(
                    "This parking slot is already booked for the selected time");
        }

        Booking booking = new Booking(
                userId,
                slotId,
                startTime,
                endTime,
                durationHours,
                "CONFIRMED");

        return bookingRepository.save(booking);
    }

    public List<Booking> getUserBookings(Long userId) {

        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }
}