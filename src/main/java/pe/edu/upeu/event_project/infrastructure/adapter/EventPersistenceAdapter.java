package pe.edu.upeu.event_project.infrastructure.adapter;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import pe.edu.upeu.event_project.domain.model.Event;
import pe.edu.upeu.event_project.domain.port.on.EventRepositoryPort;
import pe.edu.upeu.event_project.infrastructure.adapter.mapper.EventMapper;
import pe.edu.upeu.event_project.infrastructure.entity.EventEntity;

import java.util.List;
import java.util.Optional;

@Component
public class EventPersistenceAdapter implements EventRepositoryPort {
  private final EventRepository eventRepository;
  private final EventMapper eventMapper;

    public EventPersistenceAdapter(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }


    @Override
    public Event save(Event event) {
        EventEntity eventEntity = eventMapper.toEntity(event);
        EventEntity saveEntity = eventRepository.save(eventEntity);
        return eventMapper.toDomainModel(saveEntity);

    }

    @Override
    @Transactional
    public Event update(Event event) {
        EventEntity eventEntity = eventRepository.findById(event.getId())
                .orElseThrow(()-> new RuntimeException("Evento no encontrado"));
        //Modificar la entidad
        eventEntity.setName(event.getName());
        eventEntity.setDescription(event.getDescription());
        eventEntity.setEventDate(event.getEventDate());
        eventEntity.setLocation(event.getLocation());
        eventEntity.setBudget(event.getBudget());
        return eventMapper.toDomainModel(eventEntity);

    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);

    }

    @Override
    public List<Event> findAll() {
        List<EventEntity> eventEntities = eventRepository.findAll();
       return eventMapper.toDomainModelList(eventEntities);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id)
                .map(eventMapper::toDomainModel);
    }

    @Override
    public List<Event> findByOrganizerId(Long organizerId) {
        return null;
        /*
        List<EventEntity> eventEntities = eventRepository.findByOrganizerId(organizerId);
       return eventMapper.toDomainModelList(eventEntities);*/
    }

    @Override
    public Optional<Event> findByIdWithTasks(Long id) {
        return null;
        /*
        return eventRepository.findByIdWithTasks(id)
                .map(eventMapper::toDomainModel);*/
    }

    @Override
    public Optional<Event> findByIdWithInvitations(Long id) {
        return null;
        /*
        return eventRepository.findByIdWithInvitations(id)
                .map(eventMapper::toDomainModel);

         */
    }
}
