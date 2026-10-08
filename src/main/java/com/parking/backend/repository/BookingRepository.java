package com.parking.backend.repository;

import com.parking.backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findBySlotId(Long slotId);

    boolean existsBySlotIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long slotId,
            LocalDateTime endTime,
            LocalDateTime startTime);
}