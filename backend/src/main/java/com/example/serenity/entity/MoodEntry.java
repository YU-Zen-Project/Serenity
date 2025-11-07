package com.example.serenity.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDateTime;

@Entity @Table(name="mood_entries") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MoodEntry {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="user_id", nullable=false) private User user;
  @Column(nullable=false) private int score;
  @Column(length=500) private String note;
  @Column(nullable=false) private LocalDateTime recordedAt = LocalDateTime.now();
}
