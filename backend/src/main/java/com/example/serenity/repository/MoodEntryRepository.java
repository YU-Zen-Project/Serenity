package com.example.serenity.repository;
import com.example.serenity.entity.MoodEntry;import com.example.serenity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> { List<MoodEntry> findByUserOrderByRecordedAtDesc(User user); }
