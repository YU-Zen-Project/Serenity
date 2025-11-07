package com.example.serenity.entity;
import jakarta.persistence.*;import lombok.*;import java.time.LocalDateTime;

@Entity @Table(name="habits") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Habit {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="user_id", nullable=false) private User user;
  @Column(nullable=false) private String title;
  @Column(nullable=false) private String type;
  @Column(nullable=false) private boolean active = true;
  @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();
}
