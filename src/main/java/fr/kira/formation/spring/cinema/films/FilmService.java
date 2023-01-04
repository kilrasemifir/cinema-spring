package fr.kira.formation.spring.cinema.films;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kira.formation.spring.cinema.films.dto.FilmCompletDto;
import fr.kira.formation.spring.cinema.films.dto.FilmReduitDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Classe Service pour les films.
 */
@Service
public class FilmService {

    private final FullFilmRepository repository;
    private final FilmJpaRepository jpaRepository;

    private final ObjectMapper mapper;


    public FilmService(FullFilmRepository repository, FilmJpaRepository jpaRepository, ObjectMapper mapper) {
        this.repository = repository;
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    /**
     * Sauvegarde un film dans la base de données.
     * Si le film posséde aucun id, alors sauvegarde le film et lui donne un id.
     * Sinon remplace le film portant l'id dans la base de données.
     * @param film a sauvegarder
     * @return film sauvegarder.
     */
    public FilmCompletDto save(Film film){
        Film entite = jpaRepository.save(film);
        return mapper.convertValue(entite, FilmCompletDto.class);
    }

    /**
     * Retourne la liste de l'ensemble des films.
     * @return liste de l'ensemble des films.
     */
    public List<FilmReduitDto> findAll(){
        List<Film> entities = this.jpaRepository.findAll();
        /*
        En JS
        let entities = ...
        return entities.map(entity => mapper.convertValue(entity, FilmReduitDto.class))
         */
        return entities.stream().map(film -> mapper.convertValue(film, FilmReduitDto.class)).toList();
    }

    /**
     * Retourne le film portant l'id.
     * @param id du film a rechercher
     * @return le film rechercher
     * @throws ResponseStatusException si aucun ne porte cet id dans la base de données,
     *      alors retourne cette exception avec le status 404 NOT_FOUND
     */
    public FilmCompletDto findById(Integer id) {
        var entity =  jpaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.convertValue(entity, FilmCompletDto.class);
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
    public List<FilmReduitDto> findByTitreContaining(String titre){
        List<Film> entities = jpaRepository.findByTitreContaining(titre);
        return entities.stream().map(film -> mapper.convertValue(film, FilmReduitDto.class)).toList();
    }
}
