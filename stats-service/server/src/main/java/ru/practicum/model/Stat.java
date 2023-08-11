package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stat")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "app", nullable = false)
    private String app;
    @Column(name = "ip", nullable = false)
    private String ip;
    @Column(name = "uri", nullable = false)
    private String uri;
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
