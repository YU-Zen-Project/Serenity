package com.example.serenity.controller;

import com.example.serenity.model.ApiResponse;
import com.example.serenity.model.AuthResponse;
import com.example.serenity.model.LoginRequest;
import com.example.serenity.model.RegisterRequest;
import com.example.serenity.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService auth;

  public AuthController(AuthService auth) {
    this.auth = auth;
  }

  @PostMapping("/register")
  public ApiResponse register(@RequestBody @Valid RegisterRequest req) {
    return auth.register(req);
  }

  @PostMapping("/login")
  public AuthResponse login(@RequestBody @Valid LoginRequest req) {
    return auth.login(req);
  }
}
