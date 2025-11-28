package com.example.serenity.controller;

import com.example.serenity.entity.Habit;
import com.example.serenity.entity.HabitLog;
import com.example.serenity.entity.User;
import com.example.serenity.service.AuthService;
import com.example.serenity.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
  private final HabitService svc;
  private final AuthService auth;

  public HabitController(HabitService svc, AuthService auth) {
    this.svc = svc;
    this.auth = auth;
  }

  @PostMapping
  public Habit create(@RequestBody Habit input, Principal principal) {
    User u = auth.getByEmail(principal.getName());
    input.setUser(u);
    return svc.create(input);
  }

  @PatchMapping("/{habitId}/check-in")
  public HabitLog checkIn(@PathVariable Long habitId,
      @RequestParam String date,
      Principal principal) {
    User u = auth.getByEmail(principal.getName());
    Habit h = svc.getById(habitId);
    LocalDate d = LocalDate.parse(date);
    return svc.checkIn(h, u, d);
  }
}
