package fr.kira.formation.spring.cinema.films;

// jakarta == JEE
// jakarta.persistence == JPA

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Ici on utilise la norme JEE JPA implémenté par l'ORM hibernate
 * JEE => ensemble des normes Java pour les applications d'entreprises
 * JPA => norme JEE pour la gestion de la persistance des données
 * Hibernate => Implémentation la plus courante de JPA pour faire un ORM
 */
// ORM = Object Relational Mapping ici hibernate
@Entity // Definie que la classe représente une entité dans la base de données
@Table(name="films") // perme de definir dans quel table se trouve cette entité
@Getter // Ajoute un getter pour chaque attribut
@Setter // Ajoute un setter pour chaque attribut
@ToString // Ajoute le toString
@NoArgsConstructor // Ajoute un constructeur sans params
public class Film {

    @Id // Define que le champ représente la clé primaire de la table
    @GeneratedValue(strategy = GenerationType.AUTO) // permet de générer l'id automatiquement
    private Integer id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name="date_sortie")
    private LocalDate date_sortie;

    private int duree;

    @Column(length = 500)
    private String resume;

    @Transient // Ignoré par la base de données
    private String attributSansLienBdd;

}
