package fr.kira.formation.spring.cinema.acteurs;

import org.springframework.stereotype.Service;

@Service
public class ActeurService {

    private final ActeurRepository repository;

    public ActeurService(ActeurRepository repository) {
        this.repository = repository;
    }

    public Acteur save(Acteur entity) {
        return repository.save(entity);
    }

    public Acteur findById(Integer integer) {
        return repository.findById(integer).orElseThrow();
    }

    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    public Iterable<Acteur> findAll() {
        return repository.findAll();
    }

}
