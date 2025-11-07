package com.example.serenity.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDate;

@Entity @Table(name="habit_logs",uniqueConstraints=@UniqueConstraint(columnNames={"habit_id","log_date"}))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HabitLog {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="habit_id", nullable=false) private Habit habit;
  @ManyToOne @JoinColumn(name="user_id", nullable=false) private User user;
  @Column(name="log_date", nullable=false) private LocalDate logDate;
  @Column(nullable=false) private int status = 1;
}
