package fr.kira.formation.spring.cinema.films;

import fr.kira.formation.spring.cinema.films.dto.FilmCompletDto;
import fr.kira.formation.spring.cinema.films.dto.FilmReduitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("films")
@CrossOrigin // evite les problèmes les CORS
public class FilmController {

    private final FilmService service;


    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public FilmCompletDto save(@RequestBody Film film) {
        return service.save(film);
    }

    @GetMapping
    public List<FilmReduitDto> findAll() {
        var res =  service.findAll();
        return res;
    }

    @GetMapping("{id}")
    public FilmCompletDto findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PutMapping
    public FilmCompletDto update(@RequestBody Film film){
        return this.service.save(film);
    }

    @GetMapping("titre/{titre}")
    public List<FilmReduitDto> findByTitre(@PathVariable String titre){
        return this.service.findByTitreContaining(titre);
    }
}
