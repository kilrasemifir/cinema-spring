package fr.kira.formation.spring.cinema.seances;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sceances")
@Getter
@Setter
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDate date;

    private int nombrePlace;

    private float prix;
}
