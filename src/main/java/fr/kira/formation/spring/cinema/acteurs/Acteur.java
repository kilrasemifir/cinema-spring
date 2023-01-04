package fr.kira.formation.spring.cinema.acteurs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fr.kira.formation.spring.cinema.films.Film;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "acteurs")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;

    private String prenom;


    /*
    Pour un many to many on a besoin d'une table de jointure qui contient
        id_film
        id_acteur
     */
    @ManyToMany
    @JoinTable(
            name = "acteur_film", // nom de la table de jointure
            joinColumns = @JoinColumn(name="id_acteur"), // nom de la colonne pour les acteurs
            inverseJoinColumns = @JoinColumn(name = "id_film")
    )
    //@JsonBackReference // pour eviter la boucle infinie
    private List<Film> films = new ArrayList<>();
}
