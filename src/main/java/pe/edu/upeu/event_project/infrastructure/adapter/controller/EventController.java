package pe.edu.upeu.event_project.infrastructure.adapter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.event_project.domain.model.Event;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.domain.port.in.EventUseCase;
import pe.edu.upeu.event_project.domain.port.in.UserUseCase;
import pe.edu.upeu.event_project.infrastructure.adapter.controller.dto.EventDto;
import pe.edu.upeu.event_project.infrastructure.adapter.controller.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    private final EventUseCase eventUseCase;
    private final UserUseCase userUseCase;

    public EventController(EventUseCase eventUseCase, UserUseCase userUseCase) {
        this.eventUseCase = eventUseCase;
        this.userUseCase = userUseCase;
    }
    @GetMapping
    public ResponseEntity<List<EventDto.EventResponse>> getAllEvents() {
        List<EventDto.EventResponse> events = eventUseCase.getAllEvents()
                .stream()
                .map(this::mapToEventResponse) // Reutilizamos el método de mapeo
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }
    @PostMapping
    // @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<EventDto.EventResponse> createEvent(@RequestBody EventDto.EventRequest eventRequest) {
        // Mapeo de DTO a Modelo
        Event eventToCreate = new Event();
        eventToCreate.setName(eventRequest.name());
        eventToCreate.setDescription(eventRequest.description());
        eventToCreate.setEventDate(eventRequest.eventDate());
        eventToCreate.setLocation(eventRequest.location());
        eventToCreate.setBudget(eventRequest.budget());

        Event createdEvent = eventUseCase.createEvent(eventToCreate);

        User organizerWithDetails = userUseCase.getUserById(createdEvent.getOrganizer().getId())
                .orElse(null);

        createdEvent.setOrganizer(organizerWithDetails);

        return new ResponseEntity<>(mapToEventResponse(createdEvent), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto.EventResponse> getEventById(@PathVariable Long id) {
        return eventUseCase.getEventWithDetails(id)
                .map(event -> ResponseEntity.ok(mapToEventResponse(event)))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<List<EventDto.EventResponse>> getEventsByOrganizer(@PathVariable Long organizerId) {
        List<EventDto.EventResponse> events = eventUseCase.getEventsByOrganizer(organizerId)
                .stream()
                .map(this::mapToEventResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<EventDto.EventResponse> updateEvent(@PathVariable Long id, @RequestBody EventDto.EventRequest eventRequest) {
        Event eventToUpdate = new Event();
        eventToUpdate.setId(id);
        eventToUpdate.setName(eventRequest.name());
        eventToUpdate.setDescription(eventRequest.description());
        eventToUpdate.setEventDate(eventRequest.eventDate());
        eventToUpdate.setLocation(eventRequest.location());
        eventToUpdate.setBudget(eventRequest.budget());

        return eventUseCase.updateEvent(id, eventToUpdate)
                .map(event -> ResponseEntity.ok(mapToEventResponse(event)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (eventUseCase.deleteEvent(id)) {
            return ResponseEntity.noContent().build(); // HTTP 204
        } else {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
    }





    //metodo de ayuda para el mapeo de datos
    private EventDto.EventResponse mapToEventResponse(Event event){
        if (event == null || event.getOrganizer() == null){
           return null;
        }
        UserDto.UserResponse organizerResponse = new UserDto.UserResponse(
                event.getOrganizer().getId(),
                event.getOrganizer().getFull_name(),
                event.getOrganizer().getEmail(),
                event.getOrganizer().getRole()
        );
        return new EventDto.EventResponse(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getEventDate(),
                event.getLocation(),
                event.getBudget(),
                organizerResponse
        );
    }

}
