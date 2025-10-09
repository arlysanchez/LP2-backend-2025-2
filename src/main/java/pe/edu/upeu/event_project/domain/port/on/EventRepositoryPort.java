package pe.edu.upeu.event_project.domain.port.on;

import pe.edu.upeu.event_project.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepositoryPort {
    Event save(Event event);
    Event update(Event event);
    void deleteById(Long id);
    List<Event> findAll();

    Optional<Event> findById(Long id);
    List<Event> findByOrganizerId(Long organizerId);
    Optional<Event> findByIdWithTasks(Long id);
    Optional<Event> findByIdWithInvitations(Long id);
    Optional<Event> updateImageUrl(Long eventId, String imageUrl);

}
