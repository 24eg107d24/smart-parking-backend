package com.parking.backend.service;

import com.parking.backend.dto.LoginRequest;
import com.parking.backend.dto.RegisterRequest;
import com.parking.backend.entity.User;
import com.parking.backend.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // REGISTER
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already registered";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password before saving
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");

        userRepository.save(user);

        return "Registration successful";
    }

    // LOGIN
    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "Invalid email or password";
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return "Invalid email or password";
        }

        // Generate JWT containing user ID, email and role
        return jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole());
    }
}