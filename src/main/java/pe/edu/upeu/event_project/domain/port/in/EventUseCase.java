package pe.edu.upeu.event_project.domain.port.in;

import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.event_project.domain.model.Event;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EventUseCase {
    Event createEvent(Event event);
    Optional<Event> updateEvent(Long eventId, Event event);
    boolean deleteEvent(Long id);
    List<Event> getAllEvents();
    Optional<Event> getAllEventsById(Long id);

    //metodos de ayuda para construir el json
    Optional<Event> getEventWithDetails(Long id);
    List<Event> getEventsByOrganizer(Long organizerId);
    Optional<Event> uploadImage(Long eventId, MultipartFile file) throws IOException;


}
