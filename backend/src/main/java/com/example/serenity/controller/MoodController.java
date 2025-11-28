package com.example.serenity.controller;

import com.example.serenity.entity.MoodEntry;
import com.example.serenity.entity.User;
import com.example.serenity.service.AuthService;
import com.example.serenity.service.MoodService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/moods")
public class MoodController {
  private final MoodService moods;
  private final AuthService auth;

  public MoodController(MoodService moods, AuthService auth) {
    this.moods = moods;
    this.auth = auth;
  }

  @GetMapping
  public List<MoodEntry> list(Principal principal) {
    User u = auth.getByEmail(principal.getName());
    return moods.listByUser(u);
  }

  @PostMapping
  public MoodEntry create(@RequestBody MoodEntry input, Principal principal) {
    User u = auth.getByEmail(principal.getName());
    input.setUser(u);
    return moods.save(input);
  }
}
