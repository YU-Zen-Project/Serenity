package com.example.serenity.service;

import com.example.serenity.entity.User;
import com.example.serenity.model.*;
import com.example.serenity.repository.UserRepository;
import com.example.serenity.config.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository users;
  private final JwtUtil jwt;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public AuthService(UserRepository users, JwtUtil jwt) {
    this.users = users;
    this.jwt = jwt;
  }

  public ApiResponse register(RegisterRequest req) {
    if (users.findByEmail(req.email()).isPresent()) {
      return new ApiResponse(false, "Email already in use");
    }
    User u = User.builder()
        .email(req.email())
        .passwordHash(encoder.encode(req.password()))
        .name(req.name())
        .build();
    users.save(u);
    return new ApiResponse(true, "Registered");
  }

  public AuthResponse login(LoginRequest req) {
    var u = users.findByEmail(req.email())
        .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    if (!encoder.matches(req.password(), u.getPasswordHash()))
      throw new RuntimeException("Invalid credentials");
    String token = jwt.generate(u.getEmail());
    return new AuthResponse(token, "Bearer", u.getId(), u.getEmail());
  }

  public User getByEmail(String email) {
    return users.findByEmail(email).orElseThrow();
  }
}
