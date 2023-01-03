package fr.kira.formation.spring.cinema.films;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("films")
@CrossOrigin // evite les problème les CORS
public class FilmController {

    private final FilmService service;


    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public Film save(@RequestBody Film film) {
        return service.save(film);
    }

    @GetMapping
    public List<Film> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Film findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @PutMapping
    public Film update(@RequestBody Film film){
        return this.service.save(film);
    }
}
