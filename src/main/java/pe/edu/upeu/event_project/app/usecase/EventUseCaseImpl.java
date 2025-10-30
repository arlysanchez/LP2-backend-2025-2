package pe.edu.upeu.event_project.app.usecase;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upeu.event_project.domain.model.Event;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.domain.port.in.EventUseCase;
import pe.edu.upeu.event_project.domain.port.on.EventRepositoryPort;
import pe.edu.upeu.event_project.domain.port.on.FileStoragePort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EventUseCaseImpl  implements EventUseCase {

    private final EventRepositoryPort eventRepositoryPort;
    private final FileStoragePort fileStoragePort;

    public EventUseCaseImpl(EventRepositoryPort eventRepositoryPort, FileStoragePort fileStoragePort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.fileStoragePort = fileStoragePort;
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
    public Optional<Event> getAllEventsById(Long id) {
        return eventRepositoryPort.findById(id);
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

    @Override
    @Transactional
    public Optional<Event> uploadImage(Long eventId, MultipartFile file) throws IOException {
        return eventRepositoryPort.findById(eventId).
                map(event ->{
                    //1. delete image ant
                    if (event.getImageUrl()!=null && !event.getImageUrl().isEmpty()){
                     fileStoragePort.delete(event.getImageUrl());
                    }
                    //2. Save the new image
                    String filename;
                    try {
                        filename = fileStoragePort.save(file);
                    }catch (IOException e){
                        throw new RuntimeException("Fallo al guardar la imagen",e);
                    }
                    //3. llamar al nuevo metodo del puerto para el adaptador
                    return eventRepositoryPort.updateImageUrl(eventId,filename)
                            .orElse(null);

                });
    }
}
