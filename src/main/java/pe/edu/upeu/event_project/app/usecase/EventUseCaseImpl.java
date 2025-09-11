package pe.edu.upeu.event_project.app.usecase;

import org.springframework.stereotype.Service;
import pe.edu.upeu.event_project.domain.model.Event;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.domain.port.in.EventUseCase;
import pe.edu.upeu.event_project.domain.port.on.EventRepositoryPort;

import java.util.List;
import java.util.Optional;

@Service
public class EventUseCaseImpl  implements EventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public EventUseCaseImpl(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }


    @Override
    public Event createEvent(Event event) {
        User organizer = new User();
        organizer.setId(3L);
        event.setOrganizer(organizer);

        return eventRepositoryPort.save(event);

    }

    @Override
    public Optional<Event> updateEvent(Long eventId, Event event) {
        return eventRepositoryPort.findById(eventId).map(
                existingEvent->{
            event.setId(eventId);
            event.setOrganizer(existingEvent.getOrganizer());
            Event update = eventRepositoryPort.update(event);
            return  update;
        });

    }

    @Override
    public boolean deleteEvent(Long id) {
        return eventRepositoryPort.findById(id).map(event->{
            eventRepositoryPort.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepositoryPort.findAll();
    }

    @Override
    public Optional<Event> getEventWithDetails(Long id) {
        //cargar el evento y sus tareas
        Optional<Event> eventWithTaskOpt = eventRepositoryPort.findByIdWithTasks(id);

        if(eventWithTaskOpt.isEmpty()){
           return Optional.empty();
        }
        //cargar el evento y sus invitaciones
        Optional<Event> eventWithInvitationsOpt =
                eventRepositoryPort.findByIdWithInvitations(id);

        //combinar los resultados
        Event finalEvent = eventWithTaskOpt.get();
        eventWithInvitationsOpt.ifPresent(
                e -> finalEvent.setInvitations(e.getInvitations()));
        return Optional.of(finalEvent);
    }

    @Override
    public List<Event> getEventsByOrganizer(Long organizerId) {
        return eventRepositoryPort.findByOrganizerId(organizerId);
    }
}
