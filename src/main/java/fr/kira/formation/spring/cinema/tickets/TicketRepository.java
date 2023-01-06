package fr.kira.formation.spring.cinema.tickets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllBySeanceId(Integer id);
}
