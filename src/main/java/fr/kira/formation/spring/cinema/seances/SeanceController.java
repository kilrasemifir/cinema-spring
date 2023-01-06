package fr.kira.formation.spring.cinema.seances;

import fr.kira.formation.spring.cinema.exceptions.BadRequestException;
import fr.kira.formation.spring.cinema.tickets.Ticket;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seances")
public class SeanceController {

    private final SeanceService service;

    public SeanceController(SeanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Seance> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public Seance findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Seance save(@RequestBody Seance entity) {
        if (entity.getSalle() == null)  throw new SeanceBadRequestException("Il faut une salle");
        if (entity.getFilm() == null)  throw new SeanceBadRequestException("Il faut un film");
        return service.save(entity);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        service.deleteById(id);
    }

    @GetMapping("{id}/tickets")
    public List<Ticket> findTickets(@PathVariable Integer id) {
        return service.findTickets(id);
    }


}
