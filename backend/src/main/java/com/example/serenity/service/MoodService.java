package com.example.serenity.service;

import com.example.serenity.entity.*;
import com.example.serenity.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MoodService {
  private final MoodEntryRepository moods;

  public MoodService(MoodEntryRepository moods) {
    this.moods = moods;
  }

  public List<MoodEntry> listByUser(User u) {
    return moods.findByUserOrderByRecordedAtDesc(u);
  }

  public MoodEntry save(MoodEntry m) {
    return moods.save(m);
  }
}
