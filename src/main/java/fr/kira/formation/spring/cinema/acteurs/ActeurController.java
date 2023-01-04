package fr.kira.formation.spring.cinema.acteurs;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acteurs")
@CrossOrigin
public class ActeurController {

    private final ActeurService service;

    public ActeurController(ActeurService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Acteur> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Acteur findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Acteur save(@RequestBody Acteur entity) {
        return service.save(entity);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }


}
