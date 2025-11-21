package com.example.serenity.service;

import com.example.serenity.entity.*;
import com.example.serenity.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class HabitService {
  private final HabitRepository habits;
  private final HabitLogRepository logs;

  public HabitService(HabitRepository habits, HabitLogRepository logs) {
    this.habits = habits;
    this.logs = logs;
  }

  public Habit create(Habit h) {
    return habits.save(h);
  }

  public HabitLog checkIn(Habit habit, User user, LocalDate date) {
    return logs.findByHabitAndLogDate(habit, date).orElseGet(() -> {
      HabitLog log = HabitLog.builder().habit(habit).user(user).logDate(date).status(1).build();
      return logs.save(log);
    });
  }
}
