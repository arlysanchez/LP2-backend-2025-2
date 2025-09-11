package pe.edu.upeu.event_project.infrastructure.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.event_project.domain.model.Event;
import pe.edu.upeu.event_project.infrastructure.entity.EventEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EventMapper {
    Event toDomainModel(EventEntity entity);
    /*
    @Mapping(target = "invitations", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    */
    EventEntity toEntity(Event event);
    List<Event> toDomainModelList(List<EventEntity> entities);
}
