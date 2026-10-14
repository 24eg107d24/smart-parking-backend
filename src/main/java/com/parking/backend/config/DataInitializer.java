package com.parking.backend.config;

import com.parking.backend.entity.ParkingLocation;
import com.parking.backend.entity.ParkingSlot;
import com.parking.backend.entity.User;
import com.parking.backend.repository.ParkingLocationRepository;
import com.parking.backend.repository.ParkingSlotRepository;
import com.parking.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ParkingLocationRepository locationRepository;
    private final ParkingSlotRepository slotRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            ParkingLocationRepository locationRepository,
            ParkingSlotRepository slotRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.locationRepository = locationRepository;
        this.slotRepository = slotRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Seed default users if none exist
        if (userRepository.count() == 0) {
            User demoUser = new User(
                    "Demo User",
                    "demo@parking.com",
                    passwordEncoder.encode("password123"),
                    "USER"
            );
            User adminUser = new User(
                    "Admin User",
                    "admin@parking.com",
                    passwordEncoder.encode("admin123"),
                    "ADMIN"
            );
            userRepository.saveAll(List.of(demoUser, adminUser));
            System.out.println("✅ Seeded default users: demo@parking.com / password123");
        }

        // Seed parking locations & slots if none exist
        if (locationRepository.count() == 0) {
            ParkingLocation loc1 = new ParkingLocation(
                    "Downtown Metro Plaza",
                    "12 Main Boulevard, Central Junction",
                    "Bengaluru",
                    12.9716,
                    77.5946,
                    6
            );

            ParkingLocation loc2 = new ParkingLocation(
                    "Cyber Heights Hub",
                    "Plot 45, Phase 2, HITEC City",
                    "Hyderabad",
                    17.4435,
                    78.3772,
                    6
            );

            ParkingLocation loc3 = new ParkingLocation(
                    "Airport Terminal Express",
                    "Terminal 1 Gate 3, Airport Corridor",
                    "Mumbai",
                    19.0896,
                    72.8656,
                    6
            );

            locationRepository.saveAll(List.of(loc1, loc2, loc3));

            List<ParkingSlot> slots = List.of(
                    // Location 1 Slots
                    new ParkingSlot("A-01", "Car", false, loc1),
                    new ParkingSlot("A-02", "Car", false, loc1),
                    new ParkingSlot("A-03", "Car", true, loc1),
                    new ParkingSlot("B-01", "Bike", false, loc1),
                    new ParkingSlot("B-02", "Bike", false, loc1),
                    new ParkingSlot("S-01", "SUV", false, loc1),

                    // Location 2 Slots
                    new ParkingSlot("C-01", "Car", false, loc2),
                    new ParkingSlot("C-02", "Car", false, loc2),
                    new ParkingSlot("C-03", "Car", false, loc2),
                    new ParkingSlot("M-01", "Bike", false, loc2),
                    new ParkingSlot("M-02", "Bike", true, loc2),
                    new ParkingSlot("X-01", "SUV", false, loc2),

                    // Location 3 Slots
                    new ParkingSlot("T-01", "Car", false, loc3),
                    new ParkingSlot("T-02", "Car", true, loc3),
                    new ParkingSlot("T-03", "Car", false, loc3),
                    new ParkingSlot("R-01", "Bike", false, loc3),
                    new ParkingSlot("R-02", "Bike", false, loc3),
                    new ParkingSlot("Z-01", "SUV", false, loc3)
            );

            slotRepository.saveAll(slots);
            System.out.println("✅ Seeded default parking locations and slots successfully.");
        }
    }
}
