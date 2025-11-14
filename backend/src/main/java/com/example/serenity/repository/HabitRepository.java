package com.example.serenity.repository;
import com.example.serenity.entity.Habit;import com.example.serenity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface HabitRepository extends JpaRepository<Habit, Long> { List<Habit> findByUserAndActiveTrue(User user); }
