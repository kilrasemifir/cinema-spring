package fr.kira.formation.spring.cinema.films;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Classe Service pour les films.
 */
@Service
public class FilmService {

    private final FilmRepository repository;
    private final FilmJpaRepository jpaRepository;


    public FilmService(FilmRepository repository, FilmJpaRepository jpaRepository) {
        this.repository = repository;
        this.jpaRepository = jpaRepository;

    }

    /**
     * Sauvegarde un film dans la base de données.
     * Si le film e posséde aucun id, alors sauvegarde le film et lui donne un id.
     * Sinon remplace le film portant l'id dans la base de données.
     * @param film a sauvegarder
     * @return film sauvegarder.
     */
    public Film save(Film film){
        return this.jpaRepository.save(film);
    }

    /**
     * Retourne la liste de l'ensemble des films.
     * @return liste de l'ensemble des films.
     */
    public List<Film> findAll(){
        return this.jpaRepository.findAll();
    }

    /**
     * Retourne le film portant l'id.
     * @param id du film a rechercher
     * @return le film rechercher
     * @throws ResponseStatusException si aucun ne porte cet id dans la base de données,
     *      alors retourne cette exception avec le status 404 NOT_FOUND
     */
    public Film findById(Integer id) {
        return jpaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Supprime le film portant l'id dans la base de données. Ne fait rien si aucun film ne porte cette id.
     * @param id du film a supprimer.
     */
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }

    /**
     * Retourne la liste des films ou leurs titre contient le mot clef.
     * @param titre a rechercher
     * @return liste des films
     */
    public List<Film> findByTitreContaining(String titre){
        return jpaRepository.findByTitreContaining(titre);
    }
}
