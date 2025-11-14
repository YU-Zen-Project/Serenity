package com.example.serenity.repository;
import com.example.serenity.entity.HabitLog;import com.example.serenity.entity.User;import com.example.serenity.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;import java.time.LocalDate;import java.util.Optional;import java.util.List;
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
  Optional<HabitLog> findByHabitAndLogDate(Habit habit, LocalDate date);
  List<HabitLog> findByUserAndLogDateBetween(User user, LocalDate from, LocalDate to);
}
