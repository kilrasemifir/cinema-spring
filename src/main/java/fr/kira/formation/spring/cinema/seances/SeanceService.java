package fr.kira.formation.spring.cinema.seances;

import fr.kira.formation.spring.cinema.exceptions.BadRequestException;
import fr.kira.formation.spring.cinema.salles.Salle;
import fr.kira.formation.spring.cinema.salles.SalleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeanceService {

    private final SeanceRepository repository;
    private final SalleService salleService;


    public SeanceService(SeanceRepository repository, SalleService salleService) {
        this.repository = repository;
        this.salleService = salleService;
    }

    public List<Seance> findAll() {
        return repository.findAll();
    }

    /**
     * Sauvegarde une seance et defini le nombre de place disponible en fonction de la capacite de la salle
     * @param entity la seance a sauvegarder
     * @return la seance sauvegarder avec son id et le nombre de place disponible
     */
    public Seance save(Seance entity) {
        Salle salle = salleService.findById(entity.getSalle().getId());
        entity.setNombrePlace(salle.getCapacite());
        return repository.save(entity);
    }

    public Seance findById(Integer integer) {
        return repository.findById(integer).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    /**
     * <h2>Retourne la liste des seances a une date donnée</h2>
     * @param date la date a laquelle on veut les seances
     */
    public List<Seance> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }


}
